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
import locacaomidias.entidades.Genero;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
public class GeneroDAO extends DAO<Genero>{
    
    public GeneroDAO() throws SQLException{
        
    }
    
    @Override
    public void salvar(Genero obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "genero( descricao ) " + 
                "VALUES( ? );",
                new String[]{ "id" } );
        
        stmt.setString(1, obj.getDescricao());
        
        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        stmt.close();
        
    }

    @Override
    public void atualizar(Genero obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE genero " +
                "SET" +
                "   descricao = ? " +
                "WHERE" +
                "   id = ?;");
        
        stmt.setString(1, obj.getDescricao());
        stmt.setLong(2, obj.getId());
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void excluir(Genero obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM genero " +
                "WHERE" +
                "   id = ?;");
        
        stmt.setLong(1, obj.getId());
        
        stmt.executeUpdate();
        stmt.close();
        
    }

    @Override
    public List listarTodos() throws SQLException {
        
        List<Genero> lista = new ArrayList<>();
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM genero " +
                "ORDER BY id;");
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            
            Genero genero = new Genero();
            
            genero.setId(rs.getLong("id"));
            genero.setDescricao(rs.getString("descricao"));
            
            lista.add(genero);
        }
        
        rs.close();
        stmt.close();
        
        return lista;
        
    }

    @Override
    public Genero obterPorId(Long id) throws SQLException {
        
        Genero genero = null;
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM genero " +
                "WHERE id = ?;");
        
        stmt.setLong(1, id);
        
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            
            genero = new Genero();
            
            genero.setId(rs.getLong("id"));
            genero.setDescricao(rs.getString("descricao"));
            
        }
        
        rs.close();
        stmt.close();
        
        return genero;
        
    }
}
