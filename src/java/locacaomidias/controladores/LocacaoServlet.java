/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.ClienteDAO;
import locacaomidias.dao.ExemplarDAO;
import locacaomidias.dao.ItemLocacaoDAO;
import locacaomidias.dao.LocacaoDAO;
import locacaomidias.entidades.Cliente;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.ItemLocacao;
import locacaomidias.entidades.Locacao;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
@WebServlet(name = "LocacaoServlet", urlPatterns = {"/processaLocacoes"})
public class LocacaoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        RequestDispatcher disp = null;

        try {

            LocacaoDAO daoLocacao = new LocacaoDAO();
            ClienteDAO daoCliente = new ClienteDAO();
            ItemLocacaoDAO daoItemLocacao = new ItemLocacaoDAO();
            ExemplarDAO daoExemplar = new ExemplarDAO();

            if (acao.equals("inserir")) {

                Long idCliente = Utils.getLong(request, "idCliente");
                String dataFim = request.getParameter("dataFim");
                String itensLocacao = request.getParameter("itensLocacao");

                JsonReader jsr = Json.createReader(new StringReader(itensLocacao));
                JsonArray jsaItensLocacao = jsr.readArray();

                Cliente cliente = daoCliente.obterPorId(idCliente);

                Locacao locacao = new Locacao();
                locacao.setDataInicio(Date.valueOf(LocalDate.now()));
                locacao.setDataFim(Utils.getDate(dataFim));
                locacao.setCancelada(false);
                locacao.setCliente(cliente);

                Utils.validar(locacao, "id");
                daoLocacao.salvar(locacao);

                for (JsonValue jsv : jsaItensLocacao) {

                    JsonObject jso = jsv.asJsonObject();

                    Long codigoInternoExemplar = Utils.getLong(jso.getString("codigoInternoExemplar"));

                    Exemplar exemplar = daoExemplar.obterPorId(codigoInternoExemplar);
                    exemplar.setDisponivel(false);

                    ItemLocacao itemLocacao = new ItemLocacao();
                    itemLocacao.setLocacao(locacao);
                    itemLocacao.setExemplar(exemplar);
                    itemLocacao.setValor(Utils.getBigDecimal(jso.getString("valorAluguelExemplar")));
                            
                    daoExemplar.atualizar(exemplar);
                    daoItemLocacao.salvar(itemLocacao);

                }

                disp = request.getRequestDispatcher("/formularios/locacoes/listagem.jsp");

            } else if (acao.equals("cancelar")) {

                Long id = Utils.getLong(request, "id");

                Locacao locacao = daoLocacao.obterPorId(id);
                locacao.setCancelada(true);
                daoLocacao.atualizar(locacao);

                for (ItemLocacao itemLocacao : daoItemLocacao.obterPorIdLocacao(id)) {
                    Exemplar exemplar = itemLocacao.getExemplar();
                    exemplar.setDisponivel(true);
                    daoExemplar.atualizarDisponibilidade(exemplar);
                }

                response.setContentType("application/json;charset=UTF-8");

                JsonObject jo = Json.createObjectBuilder().add("status", "ok").build();

                try (PrintWriter out = response.getWriter()) {
                    out.print(jo);
                }

            }

        } catch (SQLException exc) {
            disp = Utils.prepararDespachoErro(request, exc.getMessage());
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
        return "LocacaoServlet";
    }

}
