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
import locacaomidias.dao.EstadoDAO;
import locacaomidias.entidades.Estado;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
@WebServlet(name = "EstadoServlet", urlPatterns = {"/processaEstados"})
public class EstadoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        RequestDispatcher disp = null;

        try {
            EstadoDAO daoEstado = new EstadoDAO();

            if (acao.equals("inserir")) {

                String nome = request.getParameter("nome");
                String sigla = request.getParameter("sigla");

                Estado estado = new Estado();
                estado.setNome(nome);
                estado.setSigla(sigla);

                Utils.validar(nome, "id");
                daoEstado.salvar(estado);

                disp = request.getRequestDispatcher("/formularios/estados/listagem.jsp");

            } else if (acao.equals("alterar")) {

                Long id = Utils.getLong(request, "id");
                String nome = request.getParameter("nome");
                String sigla = request.getParameter("sigla");

                Estado estado = daoEstado.obterPorId(id);
                estado.setNome(nome);
                estado.setSigla(sigla);

                Utils.validar(nome);
                daoEstado.atualizar(estado);

                disp = request.getRequestDispatcher("/formularios/estados/listagem.jsp");

            } else if (acao.equals("excluir")) {

                Long id = Utils.getLong(request, "id");
                Estado estado = daoEstado.obterPorId(id);

                daoEstado.excluir(estado);

                disp = request.getRequestDispatcher("/formularios/estados/listagem.jsp");

            } else {

                Long id = Utils.getLong(request, "id");
                Estado estado = daoEstado.obterPorId(id);

                request.setAttribute("estado", estado);

                if (acao.equals("prepararAlteracao")) {
                    disp = request.getRequestDispatcher("/formularios/estados/alterar.jsp");
                } else if (acao.equals("prepararExclusao")) {
                    disp = request.getRequestDispatcher("/formularios/estados/excluir.jsp");
                }
            }
        } catch (SQLException e) {
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
        return "EstadoServlet";
    }

}
