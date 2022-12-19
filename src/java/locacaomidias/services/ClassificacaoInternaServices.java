/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.ClassificacaoInternaDAO;
import locacaomidias.entidades.ClassificacaoInterna;

/**
 *
 * @author Filipe
 */
public class ClassificacaoInternaServices {
    
    public List<ClassificacaoInterna> getTodos(){
        
        List<ClassificacaoInterna> lista = new ArrayList<>();
        ClassificacaoInternaDAO dao = null;
        
        try{
            dao = new ClassificacaoInternaDAO();
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
