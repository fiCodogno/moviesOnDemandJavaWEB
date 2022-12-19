/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.ClienteDAO;
import locacaomidias.entidades.Cliente;

/**
 *
 * @author Filipe
 */
public class ClienteServices {
    
    public List<Cliente> getTodos(){
        
        List<Cliente> lista = new ArrayList<>();
        ClienteDAO dao = null;
        
        try{
            dao = new ClienteDAO();
            lista = dao.listarTodos();
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            if(dao != null){
                try{
                    dao.closeConnection();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
        return lista;
    }
    
}
