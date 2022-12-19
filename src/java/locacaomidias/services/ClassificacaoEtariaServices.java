/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.ClassificacaoEtariaDAO;
import locacaomidias.entidades.ClassificacaoEtaria;

/**
 *
 * @author Filipe
 */
public class ClassificacaoEtariaServices {
    
    public List<ClassificacaoEtaria> getTodos(){
        
        List<ClassificacaoEtaria> lista = new ArrayList<>();
        ClassificacaoEtariaDAO dao = null;
        
        try{
            dao = new ClassificacaoEtariaDAO();
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
