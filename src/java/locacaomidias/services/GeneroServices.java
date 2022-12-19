/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.GeneroDAO;
import locacaomidias.entidades.Genero;

/**
 *
 * @author Filipe
 */
public class GeneroServices {
    
    public List<Genero> getTodos(){
        
        List<Genero> lista = new ArrayList<>();
        GeneroDAO dao = null;
        
        try{
            dao = new GeneroDAO();
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
