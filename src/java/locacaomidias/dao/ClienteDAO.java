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
import locacaomidias.entidades.Cidade;
import locacaomidias.entidades.Cliente;
import locacaomidias.entidades.Estado;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
public class ClienteDAO extends DAO<Cliente> {
    
    public ClienteDAO() throws SQLException{
        
    }

    @Override
    public void salvar(Cliente obj) throws SQLException {
        
        PreparedStatement stmt = getConnecction().prepareStatement(
                "INSERT INTO " +
                "cliente(" +
                "   nome, " +
                "   sobrenome, " +
                "   dataNascimento, " +
                "   cpf, " +
                "   email, " +
                "   logradouro, " +
                "   numero, " +
                "   bairro, " +
                "   cep, " +
                "   cidade_id ) " +
                "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );",
                new String[]{ "id" } );
        
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getSobrenome());
        stmt.setDate(3, obj.getDataNascimento());
        stmt.setString(4, obj.getCpf());
        stmt.setString(5, obj.getEmail());
        stmt.setString(6, obj.getLogradouro());
        stmt.setString(7, obj.getNumero());
        stmt.setString(8, obj.getBairro());
        stmt.setString(9, obj.getCep());
        stmt.setLong(10, obj.getCidade().getId());
        
        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        stmt.close();
        
    }

    @Override
    public void atualizar(Cliente obj) throws SQLException {
        
        PreparedStatement stmt = getConnecction().prepareStatement(
                "UPDATE cliente " +
                "SET" +
                "   nome = ?, " +
                "   sobrenome = ?, " +
                "   dataNascimento = ?, " +
                "   cpf = ?, " +
                "   email = ?, " +
                "   logradouro = ?, " +
                "   numero = ?, " +
                "   bairro = ?, " +
                "   cep = ?, " +
                "   cidade_id = ? " +
                "WHERE" +
                "   id = ?;");
        
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getSobrenome());
        stmt.setDate(3, obj.getDataNascimento());
        stmt.setString(4, obj.getCpf());
        stmt.setString(5, obj.getEmail());
        stmt.setString(6, obj.getLogradouro());
        stmt.setString(7, obj.getNumero());
        stmt.setString(8, obj.getBairro());
        stmt.setString(9, obj.getCep());
        stmt.setLong(10, obj.getCidade().getId());
        stmt.setLong(11, obj.getId());
        
        stmt.executeUpdate();
        stmt.close();
        
    }

    @Override
    public void excluir(Cliente obj) throws SQLException {
    
        PreparedStatement stmt = getConnecction().prepareStatement(
                "DELETE FROM cliente " +
                "WHERE" +
                "   id = ?;");
        
        stmt.setLong(1, obj.getId());
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<Cliente> listarTodos() throws SQLException {
        
        List<Cliente> lista = new ArrayList<>();
        
        PreparedStatement stmt = getConnecction().prepareStatement(
                "SELECT" +
                "   c.id idCliente, " +
                "   c.nome nomeCliente, " +
                "   c.sobrenome sobrenomeCliente, " +
                "   c.dataNascimento dataNascimentoCliente, " +
                "   c.cpf cpfCliente, " +
                "   c.email emailCliente, " +
                "   c.logradouro logradouroCliente, " +
                "   c.numero numeroCliente, " +
                "   c.bairro bairroCliente, " +
                "   c.cep cepCliente, " +
                "   ci.id idCidade, " +
                "   ci.nome nomeCidade, " +
                "   e.id idEstado, " +
                "   e.nome nomeEstado, " +
                "   e.sigla siglaEstado " +
                "FROM" +
                "   cliente c, " +
                "   cidade ci, " +
                "   estado e " +
                "WHERE" +
                "   c.cidade_id = ci.id AND " +
                "   ci.estado_id = e.id " +
                "ORDER BY c.id;");
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            
            Cliente cliente = new Cliente();
            
            Estado estado = new Estado();
            estado.setId(rs.getLong("idEstado"));
            estado.setNome(rs.getString("nomeEstado"));
            estado.setSigla(rs.getString("siglaEstado"));
            
            Cidade cidade = new Cidade();
            cidade.setId(rs.getLong("idCidade"));
            cidade.setNome(rs.getString("nomeCidade"));
            cidade.setEstado(estado);
            
            cliente.setId( rs.getLong( "idCliente" ) );
            cliente.setNome( rs.getString( "nomeCliente" ) );
            cliente.setSobrenome( rs.getString( "sobrenomeCliente" ) );
            cliente.setDataNascimento( rs.getDate( "dataNascimentoCliente" ) );
            cliente.setCpf( rs.getString( "cpfCliente" ) );
            cliente.setEmail( rs.getString( "emailCliente" ) );
            cliente.setLogradouro( rs.getString( "logradouroCliente" ) );
            cliente.setNumero( rs.getString( "numeroCliente" ) );
            cliente.setBairro( rs.getString( "bairroCliente" ) );
            cliente.setCep( rs.getString( "cepCliente" ) );
            cliente.setCidade( cidade );
            
            lista.add(cliente);
        }
        
        rs.close();
        stmt.close();
        
        return lista;
    }

    @Override
    public Cliente obterPorId(Long id) throws SQLException {
        
        Cliente cliente = null;
        
        PreparedStatement stmt = getConnecction().prepareStatement(
                "SELECT" +
                "   c.id idCliente, " +
                "   c.nome nomeCliente, " +
                "   c.sobrenome sobrenomeCliente, " +
                "   c.dataNascimento dataNascimentoCliente, " +
                "   c.cpf cpfCliente, " +
                "   c.email emailCliente, " +
                "   c.logradouro logradouroCliente, " +
                "   c.numero numeroCliente, " +
                "   c.bairro bairroCliente, " +
                "   c.cep cepCliente, " +
                "   ci.id idCidade, " +
                "   ci.nome nomeCidade, " +
                "   e.id idEstado, " +
                "   e.nome nomeEstado, " +
                "   e.sigla siglaEstado " +
                "FROM" +
                "   cliente c, " +
                "   cidade ci, " +
                "   estado e " +
                "WHERE" +
                "   c.id = ? AND " +
                "   c.cidade_id = ci.id AND " +
                "   ci.estado_id = e.id; ");
        
        stmt.setLong(1, id);
        
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            
            cliente = new Cliente();
            
            Estado estado = new Estado();
            estado.setId(rs.getLong("idEstado"));
            estado.setNome(rs.getString("nomeEstado"));
            estado.setSigla(rs.getString("siglaEstado"));
            
            Cidade cidade = new Cidade();
            cidade.setId(rs.getLong("idCidade"));
            cidade.setNome(rs.getString("nomeCidade"));
            cidade.setEstado(estado);
            
            cliente.setId( rs.getLong( "idCliente" ) );
            cliente.setNome( rs.getString( "nomeCliente" ) );
            cliente.setSobrenome( rs.getString( "sobrenomeCliente" ) );
            cliente.setDataNascimento( rs.getDate( "dataNascimentoCliente" ) );
            cliente.setCpf( rs.getString( "cpfCliente" ) );
            cliente.setEmail( rs.getString( "emailCliente" ) );
            cliente.setLogradouro( rs.getString( "logradouroCliente" ) );
            cliente.setNumero( rs.getString( "numeroCliente" ) );
            cliente.setBairro( rs.getString( "bairroCliente" ) );
            cliente.setCep( rs.getString( "cepCliente" ) );
            cliente.setCidade( cidade );
        }
        
        rs.close();
        stmt.close();
        
        return cliente;
        
    }
    
    
}
