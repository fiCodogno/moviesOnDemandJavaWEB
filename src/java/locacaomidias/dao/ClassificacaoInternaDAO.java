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
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
public class ClassificacaoInternaDAO extends DAO<ClassificacaoInterna>{
    
    public ClassificacaoInternaDAO() throws SQLException{
        
    }
    
    @Override
    public void salvar(ClassificacaoInterna obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "classificacao_interna( descricao, valorAluguel ) " + 
                "VALUES( ?, ? );",
                new String[]{ "id" } );
        
        stmt.setString(1, obj.getDescricao());
        stmt.setBigDecimal(2, obj.getValorAluguel());
        
        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        stmt.close();
        
    }

    @Override
    public void atualizar(ClassificacaoInterna obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE classificacao_interna " +
                "SET" +
                "   descricao = ?, " +
                "   valorAluguel = ?" +
                "WHERE" +
                "   id = ?;");
        
        stmt.setString(1, obj.getDescricao());
        stmt.setBigDecimal(2, obj.getValorAluguel());
        stmt.setLong(3, obj.getId());
        
        stmt.executeUpdate();
        stmt.close();
        
    }

    @Override
    public void excluir(ClassificacaoInterna obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM classificacao_interna " +
                "WHERE" +
                "   id = ?;");
        
        stmt.setLong(1, obj.getId());
        
        stmt.executeUpdate();
        stmt.close();
        
    }

    @Override
    public List listarTodos() throws SQLException {
        
        List<ClassificacaoInterna> lista = new ArrayList<>();
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM classificacao_interna " +
                "ORDER BY id;" );
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            
            ClassificacaoInterna c = new ClassificacaoInterna();
            
            c.setId(rs.getLong("id"));
            c.setDescricao(rs.getString("descricao"));
            c.setValorAluguel(rs.getBigDecimal("valorAluguel"));
            
            lista.add(c);
        }
        
        rs.close();
        stmt.close();
        
        return lista;
        
    }

    @Override
    public ClassificacaoInterna obterPorId(Long id) throws SQLException {
        
        ClassificacaoInterna c = null;
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM classificacao_interna " +
                "WHERE id = ?;" );
        
        stmt.setLong(1, id);
        
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            
            c = new ClassificacaoInterna();
            
            c.setId(rs.getLong("id"));
            c.setDescricao(rs.getString("descricao"));
            c.setValorAluguel(rs.getBigDecimal("valorAluguel"));
            
        }
        
        rs.close();
        stmt.close();
        
        return c;
        
    }
    
}
