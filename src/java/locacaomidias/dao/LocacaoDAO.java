/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static locacaomidias.jdbc.ConnectionFactory.getConnection;
import locacaomidias.entidades.Cidade;
import locacaomidias.entidades.Cliente;
import locacaomidias.entidades.Estado;
import locacaomidias.entidades.Locacao;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
public class LocacaoDAO extends DAO<Locacao> {

    public LocacaoDAO() throws SQLException {

    }

    @Override
    public void salvar(Locacao obj) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO "
                + "locacao("
                + "   dataInicio, "
                + "   dataFim, "
                + "   cancelada, "
                + "   cliente_id ) "
                + "VALUES( ?, ?, ?, ?);",
                new String[]{"id"});

        stmt.setDate(1, obj.getDataInicio());
        stmt.setDate(2, obj.getDataFim());
        stmt.setBoolean(3, obj.isCancelada());
        stmt.setLong(4, obj.getCliente().getId());

        stmt.executeUpdate();
        obj.setId(Utils.getChavePrimariaAposInsercao(stmt, "id"));
        stmt.close();

    }

    @Override
    public void atualizar(Locacao obj) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE locacao "
                + "SET"
                + "   dataInicio = ?, "
                + "   dataFim = ?, "
                + "   cancelada = ?, "
                + "   cliente_id = ? "
                + "WHERE"
                + "   id = ?;");

        stmt.setDate(1, obj.getDataInicio());
        stmt.setDate(2, obj.getDataFim());
        stmt.setBoolean(3, obj.isCancelada());
        stmt.setLong(4, obj.getCliente().getId());
        stmt.setLong(5, obj.getId());

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public void excluir(Locacao obj) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM locacao "
                + "WHERE"
                + "   id = ?;");

        stmt.setLong(1, obj.getId());

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public List<Locacao> listarTodos() throws SQLException {

        List<Locacao> lista = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT"
                + "   l.id idLocacao, "
                + "   l.dataInicio dataInicioLocacao, "
                + "   l.dataFim dataFimLocacao, "
                + "   l.cancelada canceladaLocacao, "
                + "   c.id idCliente, "
                + "   c.nome nomeCliente, "
                + "   c.sobrenome sobrenomeCliente, "
                + "   c.dataNascimento dataNascimentoCliente, "
                + "   c.cpf cpfCliente, "
                + "   c.email emailCliente, "
                + "   c.logradouro logradouroCliente, "
                + "   c.numero numeroCliente, "
                + "   c.bairro bairroCliente, "
                + "   c.cep cepCliente, "
                + "   ci.id idCidade, "
                + "   ci.nome nomeCidade, "
                + "   e.id idEstado, "
                + "   e.nome nomeEstado, "
                + "   e.sigla siglaEstado "
                + "FROM"
                + "   locacao l, "
                + "   cliente c, "
                + "   cidade ci, "
                + "   estado e "
                + "WHERE"
                + "   l.cliente_id = c.id AND"
                + "   c.cidade_id = ci.id AND "
                + "   ci.estado_id = e.id "
                + "ORDER BY l.id;");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Locacao locacao = new Locacao();

            Estado estado = new Estado();
            estado.setId(rs.getLong("idEstado"));
            estado.setNome(rs.getString("nomeEstado"));
            estado.setSigla(rs.getString("siglaEstado"));

            Cidade cidade = new Cidade();
            cidade.setId(rs.getLong("idCidade"));
            cidade.setNome(rs.getString("nomeCidade"));
            cidade.setEstado(estado);

            Cliente cliente = new Cliente();
            cliente.setId(rs.getLong("idCliente"));
            cliente.setNome(rs.getString("nomeCliente"));
            cliente.setSobrenome(rs.getString("sobrenomeCliente"));
            cliente.setDataNascimento(rs.getDate("dataNascimentoCliente"));
            cliente.setCpf(rs.getString("cpfCliente"));
            cliente.setEmail(rs.getString("emailCliente"));
            cliente.setLogradouro(rs.getString("logradouroCliente"));
            cliente.setNumero(rs.getString("numeroCliente"));
            cliente.setBairro(rs.getString("bairroCliente"));
            cliente.setCep(rs.getString("cepCliente"));
            cliente.setCidade(cidade);

            locacao.setId(rs.getLong("idLocacao"));
            locacao.setDataInicio(rs.getDate("dataInicioLocacao"));
            locacao.setDataFim(rs.getDate("dataFimLocacao"));
            locacao.setCancelada(rs.getBoolean("canceladaLocacao"));
            locacao.setCliente(cliente);

            lista.add(locacao);

        }

        rs.close();
        stmt.close();

        return lista;
    }

    @Override
    public Locacao obterPorId(Long id) throws SQLException {

        Locacao locacao = null;

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT"
                + "   l.id idLocacao, "
                + "   l.dataInicio dataInicioLocacao, "
                + "   l.dataFim dataFimLocacao, "
                + "   l.cancelada canceladaLocacao, "
                + "   c.id idCliente, "
                + "   c.nome nomeCliente, "
                + "   c.sobrenome sobrenomeCliente, "
                + "   c.dataNascimento dataNascimentoCliente, "
                + "   c.cpf cpfCliente, "
                + "   c.email emailCliente, "
                + "   c.logradouro logradouroCliente, "
                + "   c.numero numeroCliente, "
                + "   c.bairro bairroCliente, "
                + "   c.cep cepCliente, "
                + "   ci.id idCidade, "
                + "   ci.nome nomeCidade, "
                + "   e.id idEstado, "
                + "   e.nome nomeEstado, "
                + "   e.sigla siglaEstado "
                + "FROM"
                + "   locacao l, "
                + "   cliente c, "
                + "   cidade ci, "
                + "   estado e "
                + "WHERE"
                + "   l.id = ? AND"
                + "   l.cliente_id = c.id AND"
                + "   c.cidade_id = ci.id AND"
                + "   ci.estado_id = e.id "
                + "ORDER BY l.id;");

        stmt.setLong(1, id);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            locacao = new Locacao();

            Estado estado = new Estado();
            estado.setId(rs.getLong("idEstado"));
            estado.setNome(rs.getString("nomeEstado"));
            estado.setSigla(rs.getString("siglaEstado"));

            Cidade cidade = new Cidade();
            cidade.setId(rs.getLong("idCidade"));
            cidade.setNome(rs.getString("nomeCidade"));
            cidade.setEstado(estado);

            Cliente cliente = new Cliente();
            cliente.setId(rs.getLong("idCliente"));
            cliente.setNome(rs.getString("nomeCliente"));
            cliente.setSobrenome(rs.getString("sobrenomeCliente"));
            cliente.setDataNascimento(rs.getDate("dataNascimentoCliente"));
            cliente.setCpf(rs.getString("cpfCliente"));
            cliente.setEmail(rs.getString("emailCliente"));
            cliente.setLogradouro(rs.getString("logradouroCliente"));
            cliente.setNumero(rs.getString("numeroCliente"));
            cliente.setBairro(rs.getString("bairroCliente"));
            cliente.setCep(rs.getString("cepCliente"));
            cliente.setCidade(cidade);

            locacao.setId(rs.getLong("idLocacao"));
            locacao.setDataInicio(rs.getDate("dataInicioLocacao"));
            locacao.setDataFim(rs.getDate("dataFimLocacao"));
            locacao.setCancelada(rs.getBoolean("canceladaLocacao"));
            locacao.setCliente(cliente);

        }

        rs.close();
        stmt.close();

        return locacao;

    }

}
