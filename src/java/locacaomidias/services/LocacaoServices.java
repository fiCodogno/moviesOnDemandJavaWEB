/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.LocacaoDAO;
import locacaomidias.entidades.Locacao;

/**
 *
 * @author Filipe
 */
public class LocacaoServices {
    
     public List<Locacao> getTodos(){
        
        List<Locacao> lista = new ArrayList<>();
        LocacaoDAO dao = null;
        
        try{
            dao = new LocacaoDAO();
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
