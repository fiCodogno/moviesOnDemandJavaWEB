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
import locacaomidias.entidades.AtorAtriz;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
public class AtorAtrizDAO extends DAO<AtorAtriz> {
    
    public AtorAtrizDAO() throws SQLException{
        
    }

    @Override
    public void salvar(AtorAtriz obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "ator_atriz( nome, sobrenome, dataEstreia ) " + 
                "VALUES( ?, ?, ? );",
                new String[]{ "id" } );
        
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getSobrenome());
        stmt.setDate(3, obj.getDataEstreia());
        
        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        stmt.close(); 
        
    }

    @Override
    public void atualizar(AtorAtriz obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE ator_atriz " +
                "SET" +
                "   nome = ?," +
                "   sobrenome = ?, " +
                "   dataEstreia = ? " +
                "WHERE" +
                "   id = ?;");
        
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getSobrenome());
        stmt.setDate(3, obj.getDataEstreia());
        stmt.setLong(4, obj.getId());
        
        stmt.executeUpdate();
        stmt.close();
        
    }

    @Override
    public void excluir(AtorAtriz obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM ator_atriz " +
                "WHERE" +
                "   id = ?;");
        
        stmt.setLong(1, obj.getId());
        
        stmt.executeUpdate();
        stmt.close(); 
        
    }

    @Override
    public List<AtorAtriz> listarTodos() throws SQLException {
        
        List<AtorAtriz> lista = new ArrayList<>();
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM ator_atriz " +
                "ORDER BY id;");
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            
            AtorAtriz ator = new AtorAtriz();
            
            ator.setId(rs.getLong("id"));
            ator.setNome(rs.getString("nome"));
            ator.setSobrenome(rs.getString("sobrenome"));
            ator.setDataEstreia(rs.getDate("dataEstreia"));
            
            lista.add(ator);
        }
        
        rs.close();
        stmt.close();
        
        return lista;
        
    }

    @Override
    public AtorAtriz obterPorId(Long id) throws SQLException {
        
        AtorAtriz ator = null;
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM ator_atriz " +
                "WHERE id = ?;");
        
        stmt.setLong(1, id);
        
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            
            ator = new AtorAtriz();
            
            ator.setId(rs.getLong("id"));
            ator.setNome(rs.getString("nome"));
            ator.setSobrenome(rs.getString("sobrenome"));
            ator.setDataEstreia(rs.getDate("dataEstreia"));
            
        }
        
        rs.close();
        stmt.close();
        
        return ator;
        
    }
    
}
