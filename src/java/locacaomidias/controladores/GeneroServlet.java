/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.controladores;

import locacaomidias.dao.GeneroDAO;
import locacaomidias.entidades.Genero;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
@WebServlet(name = "GeneroServlet", urlPatterns = {"/processaGeneros"})
public class GeneroServlet extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        GeneroDAO daoGenero = null;
        RequestDispatcher disp = null;

        try {
            
            daoGenero = new GeneroDAO();

            if (acao.equals("inserir")) {

                String descricao = request.getParameter("descricao");

                Genero genero = new Genero();
                genero.setDescricao(descricao);
                
                Utils.validar(genero, "id");
                daoGenero.salvar(genero);

                disp = request.getRequestDispatcher("/formularios/generos/listagem.jsp");

            } else if (acao.equals("alterar")) {

                Long id = Utils.getLong(request, "id");
                String descricao = request.getParameter("descricao");

                Genero genero = daoGenero.obterPorId(id);
                genero.setDescricao(descricao);

                Utils.validar(genero);
                daoGenero.atualizar(genero);

                disp = request.getRequestDispatcher("/formularios/generos/listagem.jsp");

            } else if (acao.equals("excluir")) {

                Long id = Utils.getLong(request, "id");
                Genero genero = daoGenero.obterPorId(id);

                daoGenero.excluir(genero);

                disp = request.getRequestDispatcher("/formularios/generos/listagem.jsp");

            } else {

                Long id = Utils.getLong(request, "id");
                Genero genero = daoGenero.obterPorId(id);
                request.setAttribute("genero", genero);

                if (acao.equals("prepararAlteracao")) {
                    disp = request.getRequestDispatcher("/formularios/generos/alterar.jsp");
                } else if (acao.equals("prepararExclusao")) {
                    disp = request.getRequestDispatcher("/formularios/generos/excluir.jsp");
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
    public String getServletInfo() {
        return "GeneroServlet";
    }

}
