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
import locacaomidias.dao.ClassificacaoEtariaDAO;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
@WebServlet(name = "ClassificacaoEtariaServlet", urlPatterns = {"/processaClassificacoesEtarias"})
public class ClassificacaoEtariaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        RequestDispatcher disp = null;
        
        try{
            
            ClassificacaoEtariaDAO daoClassificacaoEtaria = new ClassificacaoEtariaDAO();
            
            if (acao.equals("inserir")){
                
                String descricao = request.getParameter("descricao");
                
                ClassificacaoEtaria classificacaoEtaria = new ClassificacaoEtaria();
                classificacaoEtaria.setDescricao(descricao);
                
                Utils.validar(classificacaoEtaria, "id");
                daoClassificacaoEtaria.salvar(classificacaoEtaria);
                
                disp = request.getRequestDispatcher("/formularios/classificacoes_etarias/listagem.jsp");
                
            } else if (acao.equals("alterar")){
                
                Long id = Utils.getLong(request, "id");
                String descricao = request.getParameter("descricao");
                
                ClassificacaoEtaria classificacaoEtaria = daoClassificacaoEtaria.obterPorId(id);
                classificacaoEtaria.setDescricao(descricao);
                
                Utils.validar(classificacaoEtaria);
                daoClassificacaoEtaria.atualizar(classificacaoEtaria);
                
                disp = request.getRequestDispatcher("/formularios/classificacoes_etarias/listagem.jsp");
                
            } else if (acao.equals("excluir")){
                
                Long id = Utils.getLong(request, "id");
                ClassificacaoEtaria classificacaoEtaria = daoClassificacaoEtaria.obterPorId(id);
                
                daoClassificacaoEtaria.excluir(classificacaoEtaria);
                
                disp = request.getRequestDispatcher("/formularios/classificacoes_etarias/listagem.jsp");
                
            } else {
                
                Long id = Utils.getLong( request, "id" );
                ClassificacaoEtaria classificacaoEtaria = daoClassificacaoEtaria.obterPorId( id );
                
                request.setAttribute( "classificacao_etaria", classificacaoEtaria );
                
                if (acao.equals("prepararAlteracao")){
                    disp = request.getRequestDispatcher("/formularios/classificacoes_etarias/alterar.jsp");
                } else if (acao.equals("prepararExclusao")){
                    disp = request.getRequestDispatcher("/formularios/classificacoes_etarias/excluir.jsp");
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
        return "ClassificacaoEtariaServlet";
    }

}
