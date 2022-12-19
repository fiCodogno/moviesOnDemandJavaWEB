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
import locacaomidias.dao.TipoDAO;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
@WebServlet(name = "TipoServlet", urlPatterns = {"/processaTipos"})
public class TipoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        RequestDispatcher disp = null;

        try {

            TipoDAO daoTipo = new TipoDAO();

            if (acao.equals("inserir")) {
                
                String descricao = request.getParameter("descricao");
                
                Tipo tipo = new Tipo();
                tipo.setDescricao(descricao);
                
                Utils.validar(tipo, "id");
                daoTipo.salvar(tipo);
                
                disp = request.getRequestDispatcher("/formularios/tipos/listagem.jsp");
                
            } else if (acao.equals("alterar")) {
                
                Long id = Utils.getLong(request, "id");
                String descricao = request.getParameter("descricao");
                
                Tipo tipo = daoTipo.obterPorId(id);
                tipo.setDescricao(descricao);
                
                Utils.validar(tipo);
                daoTipo.atualizar(tipo);
                
                disp = request.getRequestDispatcher("/formularios/tipos/listagem.jsp");
                
            } else if (acao.equals("excluir")) {
                
                Long id = Utils.getLong(request, "id");
                Tipo tipo = daoTipo.obterPorId(id);
                
                daoTipo.excluir(tipo);
                
                disp = request.getRequestDispatcher("/formularios/tipos/listagem.jsp");
                
            } else {
                
                Long id = Utils.getLong(request, "id");
                Tipo tipo = daoTipo.obterPorId(id);
                
                request.setAttribute("tipo", tipo);
                
                if (acao.equals("prepararAlteracao")) {
                    disp = request.getRequestDispatcher("/formularios/tipos/alterar.jsp");
                } else if (acao.equals("prepararExclusao")) {
                    disp = request.getRequestDispatcher("/formularios/tipos/excluir.jsp");
                }
            }

        } catch (SQLException e) {
            disp = Utils.prepararDespachoErro(request, e.getMessage());
        }

        if (disp != null) {
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
        return "TipoServlet";
    }

}
