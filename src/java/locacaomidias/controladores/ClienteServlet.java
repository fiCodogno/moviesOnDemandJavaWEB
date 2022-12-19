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
import locacaomidias.dao.ClienteDAO;
import locacaomidias.entidades.Cidade;
import locacaomidias.entidades.Cliente;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/processaClientes"})
public class ClienteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        RequestDispatcher disp = null;

        try {

            ClienteDAO daoCliente = new ClienteDAO();
            CidadeDAO daoCidade = new CidadeDAO();

            if (acao.equals("inserir")) {

                String nome = request.getParameter("nome");
                String sobrenome = request.getParameter("sobrenome");
                String dataNascimento = request.getParameter("dataNascimento");
                String cpf = request.getParameter("cpf");
                String email = request.getParameter("email");
                String logradouro = request.getParameter("logradouro");
                String numero = request.getParameter("numero");
                String bairro = request.getParameter("bairro");
                String cep = request.getParameter("cep");
                Long idCidade = Utils.getLong(request, "idCidade");

                Cidade cidade = daoCidade.obterPorId(idCidade);

                Cliente cliente = new Cliente();
                cliente.setNome(nome);
                cliente.setSobrenome(sobrenome);
                cliente.setDataNascimento(Utils.getDate(dataNascimento));
                cliente.setCpf(cpf);
                cliente.setEmail(email);
                cliente.setLogradouro(logradouro);
                cliente.setNumero(numero);
                cliente.setBairro(bairro);
                cliente.setCep(cep);
                cliente.setCidade(cidade);

                Utils.validar(cliente, "id");
                daoCliente.salvar(cliente);

                disp = request.getRequestDispatcher("/formularios/clientes/listagem.jsp");

            } else if (acao.equals("alterar")) {

                Long id = Utils.getLong(request, "id");
                String nome = request.getParameter("nome");
                String sobrenome = request.getParameter("sobrenome");
                String dataNascimento = request.getParameter("dataNascimento");
                String cpf = request.getParameter("cpf");
                String email = request.getParameter("email");
                String logradouro = request.getParameter("logradouro");
                String numero = request.getParameter("numero");
                String bairro = request.getParameter("bairro");
                String cep = request.getParameter("cep");
                Long idCidade = Utils.getLong(request, "idCidade");

                Cidade cidade = daoCidade.obterPorId(idCidade);

                Cliente cliente = daoCliente.obterPorId(id);
                cliente.setNome(nome);
                cliente.setSobrenome(sobrenome);
                cliente.setDataNascimento(Utils.getDate(dataNascimento));
                cliente.setCpf(cpf);
                cliente.setEmail(email);
                cliente.setLogradouro(logradouro);
                cliente.setNumero(numero);
                cliente.setBairro(bairro);
                cliente.setCep(cep);
                cliente.setCidade(cidade);

                Utils.validar(cliente);
                daoCliente.atualizar(cliente);

                disp = request.getRequestDispatcher("/formularios/clientes/listagem.jsp");

            } else if (acao.equals("excluir")) {

                Long id = Utils.getLong(request, "id");
                Cliente cliente = daoCliente.obterPorId(id);

                daoCliente.excluir(cliente);

                disp = request.getRequestDispatcher("/formularios/clientes/listagem.jsp");

            } else {

                Long id = Utils.getLong(request, "id");
                Cliente cliente = daoCliente.obterPorId(id);
                request.setAttribute("cliente", cliente);

                if (acao.equals("prepararAlteracao")) {
                    disp = request.getRequestDispatcher("/formularios/clientes/alterar.jsp");
                } else if (acao.equals("prepararExclusao")) {
                    disp = request.getRequestDispatcher("/formularios/clientes/excluir.jsp");
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
        return "ClienteServlet";
    }

}
