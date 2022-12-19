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
import locacaomidias.dao.CidadeDAO;
import locacaomidias.dao.EstadoDAO;
import locacaomidias.entidades.Cidade;
import locacaomidias.entidades.Estado;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
@WebServlet(name = "CidadeServlet", urlPatterns = {"/processaCidades"})
public class CidadeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        RequestDispatcher disp = null;

        try {

            CidadeDAO daoCidade = new CidadeDAO();
            EstadoDAO daoEstado = new EstadoDAO();

            if (acao.equals("inserir")) {

                String nome = request.getParameter("nome");
                Long idEstado = Utils.getLong(request, "idEstado");

                Estado estado = daoEstado.obterPorId(idEstado);

                Cidade cidade = new Cidade();
                cidade.setNome(nome);
                cidade.setEstado(estado);

                Utils.validar(cidade, "id");
                daoCidade.salvar(cidade);

                disp = request.getRequestDispatcher("/formularios/cidades/listagem.jsp");

            } else if (acao.equals("alterar")) {

                Long id = Utils.getLong(request, "id");
                String nome = request.getParameter("nome");
                Long idEstado = Utils.getLong(request, "idEstado");

                Estado estado = daoEstado.obterPorId(idEstado);

                Cidade cidade = daoCidade.obterPorId(id);
                cidade.setNome(nome);
                cidade.setEstado(estado);

                Utils.validar(cidade);
                daoCidade.atualizar(cidade);

                disp = request.getRequestDispatcher("/formularios/cidades/listagem.jsp");

            } else if (acao.equals("excluir")) {

                Long id = Utils.getLong(request, "id");
                Cidade cidade = daoCidade.obterPorId(id);

                daoCidade.excluir(cidade);

                disp = request.getRequestDispatcher("/formularios/cidades/listagem.jsp");

            } else {

                Long id = Utils.getLong(request, "id");
                Cidade cidade = daoCidade.obterPorId(id);

                request.setAttribute("cidade", cidade);

                if (acao.equals("prepararAlteracao")) {
                    disp = request.getRequestDispatcher("/formularios/cidades/alterar.jsp");
                } else if (acao.equals("prepararExclusao")) {
                    disp = request.getRequestDispatcher("/formularios/cidades/excluir.jsp");
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
        return "CidadeServlet";
    }

}
