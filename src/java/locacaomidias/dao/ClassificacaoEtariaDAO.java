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
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
public class ClassificacaoEtariaDAO extends DAO<ClassificacaoEtaria>{
    
    public ClassificacaoEtariaDAO() throws SQLException{
        
    }
    
    @Override
    public void salvar(ClassificacaoEtaria obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "classificacao_etaria( descricao ) " + 
                "VALUES( ? );",
                new String[]{ "id" } );
        
        stmt.setString(1, obj.getDescricao());
        
        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        stmt.close();
        
    }

    @Override
    public void atualizar(ClassificacaoEtaria obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE classificacao_etaria " +
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
    public void excluir(ClassificacaoEtaria obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM classificacao_etaria " +
                "WHERE" +
                "   id = ?;");
        
        stmt.setLong(1, obj.getId());
        
        stmt.executeUpdate();
        stmt.close();
        
    }

    @Override
    public List listarTodos() throws SQLException {
        
        List<ClassificacaoEtaria> lista = new ArrayList<>();
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM classificacao_etaria " +
                "ORDER BY id;" );
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            
            ClassificacaoEtaria c = new ClassificacaoEtaria();
            
            c.setId(rs.getLong("id"));
            c.setDescricao(rs.getString("descricao"));
            
            lista.add(c);
        }
        
        rs.close();
        stmt.close();
        
        return lista;
        
    }

    @Override
    public ClassificacaoEtaria obterPorId(Long id) throws SQLException {
        
        ClassificacaoEtaria c = null;
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM classificacao_etaria " +
                "WHERE id = ?;" );
        
        stmt.setLong(1, id);
        
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            
            c = new ClassificacaoEtaria();
            
            c.setId(rs.getLong("id"));
            c.setDescricao(rs.getString("descricao"));
            
        }
        
        rs.close();
        stmt.close();
        
        return c;
        
    }
  
}
