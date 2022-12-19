/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.controladores;

import locacaomidias.dao.MidiaDAO;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Tipo;
import locacaomidias.entidades.Genero;
import locacaomidias.entidades.AtorAtriz;
import locacaomidias.entidades.Midia;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.AtorAtrizDAO;
import locacaomidias.dao.ClassificacaoEtariaDAO;
import locacaomidias.dao.ClassificacaoInternaDAO;
import locacaomidias.dao.GeneroDAO;
import locacaomidias.dao.TipoDAO;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
@WebServlet(name = "MidiasServlet", urlPatterns = {"/processaMidias"})
public class MidiaServlet extends HttpServlet {

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
        RequestDispatcher disp = null;

        try {

            MidiaDAO daoMidia = new MidiaDAO();
            ClassificacaoEtariaDAO daoClassificacaoEtaria = new ClassificacaoEtariaDAO();
            ClassificacaoInternaDAO daoClassificacaoInterna = new ClassificacaoInternaDAO();
            TipoDAO daoTipo = new TipoDAO();
            GeneroDAO daoGenero = new GeneroDAO();
            AtorAtrizDAO daoAtorAtriz = new AtorAtrizDAO();

            if (acao.equals("inserir")) {

                String titulo = request.getParameter("titulo");
                String anoLancamento = request.getParameter("anoLancamento");
                String codigoBarras = request.getParameter("codigoBarras");
                Long duracao = Utils.getLong(request, "duracao");
                Long idAtorPrincipal = Utils.getLong(request, "idAtorPrincipal");
                Long idAtorCoadjuvante = Utils.getLong(request, "idAtorCoadjuvante");
                Long idGenero = Utils.getLong(request, "idGenero");
                Long idClassificacaoEtaria = Utils.getLong(request, "idClassificacaoEtaria");
                Long idTipo = Utils.getLong(request, "idTipo");
                Long idClassificacaoInterna = Utils.getLong(request, "idClassificacaoInterna");

                AtorAtriz atorPrincipal = daoAtorAtriz.obterPorId(idAtorPrincipal);
                AtorAtriz atorCoadjuvante = daoAtorAtriz.obterPorId(idAtorCoadjuvante);
                Genero genero = daoGenero.obterPorId(idGenero);
                ClassificacaoEtaria classificacaoEtaria = daoClassificacaoEtaria.obterPorId(idClassificacaoEtaria);
                Tipo tipo = daoTipo.obterPorId(idTipo);
                ClassificacaoInterna classificacaoInterna = daoClassificacaoInterna.obterPorId(idClassificacaoInterna);

                Midia midia = new Midia();
                midia.setTitulo(titulo);
                midia.setAnoLancamento(anoLancamento);
                midia.setCodigoBarras(codigoBarras);
                midia.setDuracaoEmMinutos(duracao);
                midia.setAtorAtrizPrincipal(atorPrincipal);
                midia.setAtorAtrizCoadjuvante(atorCoadjuvante);
                midia.setGenero(genero);
                midia.setClassificacaoEtaria(classificacaoEtaria);
                midia.setTipo(tipo);
                midia.setClassificacaoInterna(classificacaoInterna);

                Utils.validar(midia, "id");
                daoMidia.salvar(midia);

                disp = request.getRequestDispatcher("/formularios/midias/listagem.jsp");

            } else if (acao.equals("alterar")) {
                
                Long id = Utils.getLong(request, "id");
                String titulo = request.getParameter("titulo");
                String anoLancamento = request.getParameter("anoLancamento");
                String codigoBarras = request.getParameter("codigoBarras");
                Long duracao = Utils.getLong(request, "duracao");
                Long idAtorPrincipal = Utils.getLong(request, "idAtorPrincipal");
                Long idAtorCoadjuvante = Utils.getLong(request, "idAtorCoadjuvante");
                Long idGenero = Utils.getLong(request, "idGenero");
                Long idClassificacaoEtaria = Utils.getLong(request, "idClassificacaoEtaria");
                Long idTipo = Utils.getLong(request, "idTipo");
                Long idClassificacaoInterna = Utils.getLong(request, "idClassificacaoInterna");

                AtorAtriz atorPrincipal = daoAtorAtriz.obterPorId(idAtorPrincipal);
                AtorAtriz atorCoadjuvante = daoAtorAtriz.obterPorId(idAtorCoadjuvante);
                Genero genero = daoGenero.obterPorId(idGenero);
                ClassificacaoEtaria classificacaoEtaria = daoClassificacaoEtaria.obterPorId(idClassificacaoEtaria);
                Tipo tipo = daoTipo.obterPorId(idTipo);
                ClassificacaoInterna classificacaoInterna = daoClassificacaoInterna.obterPorId(idClassificacaoInterna);

                Midia midia = daoMidia.obterPorId(id);
                midia.setTitulo(titulo);
                midia.setAnoLancamento(anoLancamento);
                midia.setCodigoBarras(codigoBarras);
                midia.setDuracaoEmMinutos(duracao);
                midia.setAtorAtrizPrincipal(atorPrincipal);
                midia.setAtorAtrizCoadjuvante(atorCoadjuvante);
                midia.setGenero(genero);
                midia.setClassificacaoEtaria(classificacaoEtaria);
                midia.setTipo(tipo);
                midia.setClassificacaoInterna(classificacaoInterna);

                Utils.validar(midia);
                daoMidia.atualizar(midia);

                disp = request.getRequestDispatcher("/formularios/midias/listagem.jsp");

            } else if (acao.equals("excluir")) {

                Long id = Utils.getLong(request, "id");
                Midia midia = daoMidia.obterPorId(id);

                daoMidia.excluir(midia);

                disp = request.getRequestDispatcher("/formularios/midias/listagem.jsp");

            } else {

                Long id = Utils.getLong(request, "id");
                Midia midia = daoMidia.obterPorId(id);
                request.setAttribute("midia", midia);

                if (acao.equals("prepararAlteracao")) {
                    disp = request.getRequestDispatcher("/formularios/midias/alterar.jsp");
                } else if (acao.equals("prepararExclusao")) {
                    disp = request.getRequestDispatcher("/formularios/midias/excluir.jsp");
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
        return "MidiaServlet";
    }

}
