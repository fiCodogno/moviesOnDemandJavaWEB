/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.controladores;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.ClassificacaoInternaDAO;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
@WebServlet(name = "ClassificacaoInternaServlet", urlPatterns = {"/processaClassificacoesInternas"})
public class ClassificacaoInternaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        RequestDispatcher disp = null;
        
        try{
            
            ClassificacaoInternaDAO daoClassificacaoInterna = new ClassificacaoInternaDAO();
            
            if (acao.equals("inserir")){
                
                String descricao = request.getParameter("descricao");
                BigDecimal valorAluguel = Utils.getBigDecimal(request, "valorAluguel");
                
                ClassificacaoInterna classificacaoInterna = new ClassificacaoInterna();
                classificacaoInterna.setDescricao(descricao);
                classificacaoInterna.setValorAluguel(valorAluguel);
                
                Utils.validar(classificacaoInterna, "id");
                daoClassificacaoInterna.salvar(classificacaoInterna);
                
                disp = request.getRequestDispatcher("/formularios/classificacoes_internas/listagem.jsp");
                
            } else if (acao.equals("alterar")){
                
                Long id = Utils.getLong(request, "id");
                String descricao = request.getParameter("descricao");
                BigDecimal valorAluguel = Utils.getBigDecimal(request, "valorAluguel");
                
                ClassificacaoInterna classificacaoInterna = daoClassificacaoInterna.obterPorId(id);
                classificacaoInterna.setDescricao(descricao);
                classificacaoInterna.setValorAluguel(valorAluguel);
                
                Utils.validar(classificacaoInterna);
                daoClassificacaoInterna.atualizar(classificacaoInterna);
                
                disp = request.getRequestDispatcher("/formularios/classificacoes_internas/listagem.jsp");
                
            } else if (acao.equals("excluir")){
                
                Long id = Utils.getLong(request, "id");
                ClassificacaoInterna classificacaoInterna = daoClassificacaoInterna.obterPorId(id);
                
                daoClassificacaoInterna.excluir(classificacaoInterna);
                
                disp = request.getRequestDispatcher("/formularios/classificacoes_internas/listagem.jsp");
                
            } else {
                
                Long id = Utils.getLong( request, "id" );
                ClassificacaoInterna classificacaoInterna = daoClassificacaoInterna.obterPorId( id );
                
                request.setAttribute( "classificacao_interna", classificacaoInterna );
                
                if (acao.equals("prepararAlteracao")){
                    disp = request.getRequestDispatcher("/formularios/classificacoes_internas/alterar.jsp");
                } else if (acao.equals("prepararExclusao")){
                    disp = request.getRequestDispatcher("/formularios/classificacoes_internas/excluir.jsp");
                }
                
            }
            
        } catch(SQLException e){
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
        return "ClassificacaoInternaServlet";
    }

}
