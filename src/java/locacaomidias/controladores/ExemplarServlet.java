/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.ExemplarDAO;
import locacaomidias.dao.MidiaDAO;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.Midia;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
@WebServlet(name = "ExemplarServlet", urlPatterns = {"/processaExemplares"})
public class ExemplarServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        RequestDispatcher disp = null;
        
        try{
            
            ExemplarDAO daoExemplar = new ExemplarDAO();
            MidiaDAO daoMidia = new MidiaDAO();
            
            if(acao.equals("inserir")){
                
                Long idMidia = Utils.getLong(request, "idMidia");
                int idDisponivel = Integer.parseInt(request.getParameter("disponivel"));
                boolean disponivel = false;
                
                if(idDisponivel == 1){
                    disponivel = true;
                }
                
                Midia midia = daoMidia.obterPorId(idMidia);
                
                Exemplar exemplar = new Exemplar();
                exemplar.setMidia(midia);
                exemplar.setDisponivel(disponivel);
                
                Utils.validar(exemplar, "codigo_interno");
                daoExemplar.salvar(exemplar);
                
                disp = request.getRequestDispatcher("/formularios/exemplares/listagem.jsp");
                
            } else if (acao.equals("alterar")) {
                
                Long codigo_interno = Utils.getLong(request, "codigoInterno");
                Long idMidia = Utils.getLong(request, "idMidia");
                int idDisponivel = Integer.parseInt(request.getParameter("disponivel"));
                boolean disponivel = false;
                
                if(idDisponivel == 1){
                    disponivel = true;
                }
                
                Midia midia = daoMidia.obterPorId(idMidia);
                
                Exemplar exemplar = daoExemplar.obterPorId(codigo_interno);
                exemplar.setMidia(midia);
                exemplar.setDisponivel(disponivel);
                
                Utils.validar(exemplar);
                daoExemplar.atualizar(exemplar);
                
                disp = request.getRequestDispatcher("/formularios/exemplares/listagem.jsp");

            } else if (acao.equals("excluir")) {

                Long codigo_interno = Utils.getLong(request, "codigoInterno");
                Exemplar exemplar = daoExemplar.obterPorId(codigo_interno);

                daoExemplar.excluir(exemplar);

                disp = request.getRequestDispatcher("/formularios/exemplares/listagem.jsp");

            } else if (acao.equals("visualizar")){
                
                Long codigoInterno = Utils.getLong(request, "codigoInternoSelect");                
                Exemplar exemplar = daoExemplar.visualizar(codigoInterno);
                
                response.setContentType("application/json;charset=UTF-8");
                Jsonb jb = JsonbBuilder.create();
                String s = null;
                
                if(exemplar == null){
                    s = "*****";
                } else if(!exemplar.isDisponivel()){
                    s = "-----";
                } else {
                    s = exemplar.getCodigo_interno() + " - "
                        + exemplar.getMidia().getTitulo() + " - " 
                        + exemplar.getMidia().getTipo().getDescricao() + " - R$"
                        + exemplar.getMidia().getClassificacaoInterna().getValorAluguel();
                }
                
                try (PrintWriter pw = response.getWriter()){           
                    pw.print(jb.toJson(s));    
                }
            
            } else {

                Long codigo_interno = Utils.getLong(request, "codigoInterno");
                Exemplar exemplar = daoExemplar.obterPorId(codigo_interno);

                request.setAttribute("exemplar", exemplar);

                if (acao.equals("prepararAlteracao")) {
                    disp = request.getRequestDispatcher("/formularios/exemplares/alterar.jsp");
                } else if (acao.equals("prepararExclusao")) {
                    disp = request.getRequestDispatcher("/formularios/exemplares/excluir.jsp");
                }
            }
            
        } catch (SQLException e){
            disp = Utils.prepararDespachoErro(request, e.getMessage());
        }
        
        if(disp != null){
            disp.forward(request, response);
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "ExemplarServlet";
    }

}
