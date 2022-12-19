/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.TipoDAO;
import locacaomidias.entidades.Tipo;

/**
 *
 * @author Filipe
 */
public class TipoServices {
    
    public List<Tipo> getTodos(){
        
        List<Tipo> lista = new ArrayList<>();
        TipoDAO dao = null;
        
        try{
            dao = new TipoDAO();
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
