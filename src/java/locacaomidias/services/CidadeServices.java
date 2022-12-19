/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.CidadeDAO;
import locacaomidias.entidades.Cidade;

/**
 *
 * @author Filipe
 */
public class CidadeServices {
    
    public List<Cidade> getTodos(){
        
        List<Cidade> lista = new ArrayList<>();
        CidadeDAO dao = null;
        
        try{
            dao = new CidadeDAO();
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
