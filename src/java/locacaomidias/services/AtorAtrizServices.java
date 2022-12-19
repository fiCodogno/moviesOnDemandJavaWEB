/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.AtorAtrizDAO;
import locacaomidias.entidades.AtorAtriz;

/**
 *
 * @author Filipe
 */
public class AtorAtrizServices {
    
    public List<AtorAtriz> getTodos(){
        
        List<AtorAtriz> lista = new ArrayList<>();
        AtorAtrizDAO dao = null;
        
        try{
            dao = new AtorAtrizDAO();
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
