/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author Filipe
 */
public class ConnectionFactory {
    
    public static Connection getConnection() throws SQLException{
        
        return DriverManager.getConnection(
            "jdbc:mariadb://localhost/locacao_midias",
            "root",
            "");
        
    }
    
}
