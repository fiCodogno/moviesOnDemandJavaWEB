/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.controladores;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.AtorAtrizDAO;
import locacaomidias.entidades.AtorAtriz;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
@WebServlet(name = "AtorAtrizServlet", urlPatterns = {"/processaAtoresAtrizes"})
public class AtorAtrizServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        RequestDispatcher disp = null;
        
        try {
            
            AtorAtrizDAO daoAtorAtriz = new AtorAtrizDAO();
            
            if (acao.equals("inserir")) {

                String nome = request.getParameter("nome");
                String sobrenome = request.getParameter("sobrenome");
                String dataEstreia = request.getParameter("dataEstreia");
                
                AtorAtriz atorAtriz = new AtorAtriz();
                atorAtriz.setNome(nome);
                atorAtriz.setSobrenome(sobrenome);
                atorAtriz.setDataEstreia(Utils.getDate(dataEstreia));
                
                Utils.validar(atorAtriz, "id");
                daoAtorAtriz.salvar(atorAtriz);

                disp = request.getRequestDispatcher("/formularios/atores_atrizes/listagem.jsp");

            } else if (acao.equals("alterar")) {

                Long id = Utils.getLong(request, "id");
                String nome = request.getParameter("nome");
                String sobrenome = request.getParameter("sobrenome");
                String dataEstreia = request.getParameter("dataEstreia");

                AtorAtriz atorAtriz = new AtorAtriz();
                atorAtriz.setId(id);
                atorAtriz.setNome(nome);
                atorAtriz.setSobrenome(sobrenome);
                atorAtriz.setDataEstreia(Utils.getDate(dataEstreia));

                Utils.validar(atorAtriz);
                daoAtorAtriz.atualizar(atorAtriz);

                disp = request.getRequestDispatcher("/formularios/atores_atrizes/listagem.jsp");

            } else if (acao.equals("excluir")) {

                Long id = Utils.getLong(request, "id");
                AtorAtriz atorAtriz = daoAtorAtriz.obterPorId(id);

                daoAtorAtriz.excluir(atorAtriz);

                disp = request.getRequestDispatcher("/formularios/atores_atrizes/listagem.jsp");

            } else {

                long id = Utils.getLong(request, "id");
                AtorAtriz atorAtriz = daoAtorAtriz.obterPorId(id);
                
                request.setAttribute("atorAtriz", atorAtriz);

                if (acao.equals("prepararAlteracao")) {
                    disp = request.getRequestDispatcher("/formularios/atores_atrizes/alterar.jsp");
                } else if (acao.equals("prepararExclusao")) {
                    disp = request.getRequestDispatcher("/formularios/atores_atrizes/excluir.jsp");
                }

            }
            
        } catch (SQLException e){
            disp = Utils.prepararDespachoErro( request, e.getMessage() );
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
        return "AtorAtrizServlet";
    }

}
