/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.ExemplarDAO;
import locacaomidias.entidades.Exemplar;

/**
 *
 * @author Filipe
 */
public class ExemplarServices {
    
    public List<Exemplar> getTodos(){
        
        List<Exemplar> lista = new ArrayList<>();
        ExemplarDAO dao = null;
        
        try{
            dao = new ExemplarDAO();
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
