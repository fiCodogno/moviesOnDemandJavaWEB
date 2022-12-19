/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.EstadoDAO;
import locacaomidias.entidades.Estado;

/**
 *
 * @author Filipe
 */
public class EstadoServices {
    
    public List<Estado> getTodos(){
        
        List<Estado> lista = new ArrayList<>();
        EstadoDAO dao = null;
        
        try{
            dao = new EstadoDAO();
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
