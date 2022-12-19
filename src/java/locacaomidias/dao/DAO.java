/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import locacaomidias.jdbc.ConnectionFactory;

/**
 *
 * @author Filipe
 */
public abstract class DAO<Tipo> {
    
    private Connection conexao;
    
    public DAO() throws SQLException{
        conexao = ConnectionFactory.getConnection();
    }
    
    public Connection getConnecction() {
        return conexao;
    }
    
    public void closeConnection() throws SQLException {
        conexao.close();
    }
    
    public abstract void salvar(Tipo obj) throws SQLException;
    public abstract void atualizar(Tipo obj) throws SQLException;
    public abstract void excluir(Tipo obj) throws SQLException;
    public abstract List<Tipo> listarTodos() throws SQLException;
    public abstract Tipo obterPorId(Long id) throws SQLException;
    
}
