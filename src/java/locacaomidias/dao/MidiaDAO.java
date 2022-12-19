/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static locacaomidias.jdbc.ConnectionFactory.getConnection;
import locacaomidias.entidades.AtorAtriz;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Genero;
import locacaomidias.entidades.Midia;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
public class MidiaDAO extends DAO<Midia>{
    
    public MidiaDAO() throws SQLException{
        
    }

    @Override
    public void salvar(Midia obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "midia(" +
                "   titulo, " +
                "   anoLancamento, " +
                "   codigoBarras, " +
                "   duracaoEmMinutos, " +
                "   ator_atriz_principal, " +
                "   ator_atriz_coadjuvante, " +
                "   genero_id, " +
                "   classificacao_etaria_id, " +
                "   tipo_id, " +
                "   classificacao_interna_id ) " +
                "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );",
                new String[]{ "id" } );
        
        stmt.setString(1, obj.getTitulo());
        stmt.setString(2, obj.getAnoLancamento());
        stmt.setString(3, obj.getCodigoBarras());
        stmt.setLong(4, obj.getDuracaoEmMinutos());
        stmt.setLong(5, obj.getAtorAtrizPrincipal().getId());
        stmt.setLong(6, obj.getAtorAtrizCoadjuvante().getId());
        stmt.setLong(7, obj.getGenero().getId());
        stmt.setLong(8, obj.getClassificacaoEtaria().getId());
        stmt.setLong(9, obj.getTipo().getId());
        stmt.setLong(10, obj.getClassificacaoInterna().getId());
        
        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        stmt.close();
        
    }

    @Override
    public void atualizar(Midia obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE midia " + 
                "SET" +
                "   titulo = ?, " +
                "   anoLancamento = ?, " +
                "   codigoBarras = ?, " +
                "   duracaoEmMinutos = ?, " +
                "   ator_atriz_principal = ?, " +
                "   ator_atriz_coadjuvante = ?, " +
                "   genero_id = ?, " +
                "   classificacao_etaria_id = ?, " +
                "   tipo_id = ?, " +
                "   classificacao_interna_id  = ?  " +
                "WHERE" +
                "   id = ?;" );
        
        stmt.setString(1, obj.getTitulo());
        stmt.setString(2, obj.getAnoLancamento());
        stmt.setString(3, obj.getCodigoBarras());
        stmt.setLong(4, obj.getDuracaoEmMinutos());
        stmt.setLong(5, obj.getAtorAtrizPrincipal().getId());
        stmt.setLong(6, obj.getAtorAtrizCoadjuvante().getId());
        stmt.setLong(7, obj.getGenero().getId());
        stmt.setLong(8, obj.getClassificacaoEtaria().getId());
        stmt.setLong(9, obj.getTipo().getId());
        stmt.setLong(10, obj.getClassificacaoInterna().getId());
        stmt.setLong(11, obj.getId());
        
        stmt.executeUpdate();
        stmt.close();
        
    }

    @Override
    public void excluir(Midia obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM midia " + 
                "WHERE" +
                "   id = ?;" );
        
        stmt.setLong(1, obj.getId());
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<Midia> listarTodos() throws SQLException {
        
        List<Midia> lista = new ArrayList<>();
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" +
                "   m.id idMidia, " +
                "   m.titulo tituloMidia, " +
                "   m.anoLancamento anoLancamentoMidia, " +
                "   m.codigoBarras codigoBarrasMidia, " +
                "   m.duracaoEmMinutos duracaoEmMinutosMidia, " +
                "   atorPrincipal.id idAtorPrincipal, " +
                "   atorPrincipal.nome nomeAtorPrincipal, " +
                "   atorPrincipal.sobrenome sobrenomeAtorPrincipal, " +
                "   atorPrincipal.dataEstreia dataEstreiaAtorPrincipal, " +
                "   atorCoadjuvante.id idAtorCoadjuvante, " +
                "   atorCoadjuvante.nome nomeAtorCoadjuvante, " +
                "   atorCoadjuvante.sobrenome sobrenomeAtorCoadjuvante, " +
                "   atorCoadjuvante.dataEstreia dataEstreiaAtorCoadjuvante, " +
                "   genero.id idGenero, " +
                "   genero.descricao descricaoGenero, " +
                "   classificacaoEtaria.id idClassificacaoEtaria, " +
                "   classificacaoEtaria.descricao descricaoClassificacaoEtaria, " +
                "   tipo.id idTipo, " +
                "   tipo.descricao descricaoTipo, " +
                "   classificacaoInterna.id idClassificacaoInterna, " +
                "   classificacaoInterna.descricao descricaoClassificacaoInterna, " +
                "   classificacaoInterna.valorAluguel valorAluguelClassificacaoInterna " +
                "FROM" +
                "   midia m, " +
                "   ator_atriz atorPrincipal, " +
                "   ator_atriz atorCoadjuvante, " +
                "   genero genero, " +
                "   classificacao_etaria classificacaoEtaria, " +
                "   tipo tipo, " +
                "   classificacao_interna classificacaoInterna " +
                "WHERE" +
                "   m.ator_atriz_principal = atorPrincipal.id AND " +
                "   m.ator_atriz_coadjuvante = atorCoadjuvante.id AND " +
                "   m.genero_id = genero.id AND " +
                "   m.classificacao_etaria_id = classificacaoEtaria.id AND " +
                "   m.tipo_id = tipo.id AND " +
                "   m.classificacao_interna_id = classificacaoInterna.id " +
                "ORDER BY m.id;");
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            
            Midia midia = new Midia();
            
            AtorAtriz atorPrincipal = new AtorAtriz();
            atorPrincipal.setId(rs.getLong("idAtorPrincipal"));
            atorPrincipal.setNome(rs.getString("nomeAtorPrincipal"));
            atorPrincipal.setSobrenome(rs.getString("sobrenomeAtorPrincipal"));
            atorPrincipal.setDataEstreia(rs.getDate("dataEstreiaAtorPrincipal"));
            
            AtorAtriz atorCoadjuvante = new AtorAtriz();
            atorCoadjuvante.setId(rs.getLong("idAtorCoadjuvante"));
            atorCoadjuvante.setNome(rs.getString("nomeAtorCoadjuvante"));
            atorCoadjuvante.setSobrenome(rs.getString("sobrenomeAtorCoadjuvante"));
            atorCoadjuvante.setDataEstreia(rs.getDate("dataEstreiaAtorCoadjuvante"));
            
            Genero genero = new Genero();
            genero.setId(rs.getLong("idGenero"));
            genero.setDescricao(rs.getString("descricaoGenero"));
            
            ClassificacaoEtaria classificacaoEtaria = new ClassificacaoEtaria();
            classificacaoEtaria.setId(rs.getLong("idClassificacaoEtaria"));
            classificacaoEtaria.setDescricao(rs.getString("descricaoClassificacaoEtaria"));
            
            Tipo tipo = new Tipo();
            tipo.setId(rs.getLong("idTipo"));
            tipo.setDescricao(rs.getString("descricaoTipo"));
            
            ClassificacaoInterna classificacaoInterna = new ClassificacaoInterna();
            classificacaoInterna.setId(rs.getLong("idClassificacaoInterna"));
            classificacaoInterna.setDescricao(rs.getString("descricaoClassificacaoInterna"));
            classificacaoInterna.setValorAluguel(rs.getBigDecimal("valorAluguelClassificacaoInterna"));
            
            midia.setId(rs.getLong("idMidia"));
            midia.setTitulo(rs.getString("tituloMidia"));
            midia.setAnoLancamento(rs.getString("anoLancamentoMidia"));
            midia.setCodigoBarras(rs.getString("codigoBarrasMidia"));
            midia.setDuracaoEmMinutos(rs.getLong("duracaoEmMinutosMidia"));
            midia.setAtorAtrizPrincipal(atorPrincipal);
            midia.setAtorAtrizCoadjuvante(atorCoadjuvante);
            midia.setGenero(genero);
            midia.setClassificacaoEtaria(classificacaoEtaria);
            midia.setTipo(tipo);
            midia.setClassificacaoInterna(classificacaoInterna);
            
            lista.add(midia);
        }
        
        rs.close();
        stmt.close();
        
        return lista;
        
    }

    @Override
    public Midia obterPorId(Long id) throws SQLException {
        
        Midia midia = null;
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" +
                "   m.id idMidia, " +
                "   m.titulo tituloMidia, " +
                "   m.anoLancamento anoLancamentoMidia, " +
                "   m.codigoBarras codigoBarrasMidia, " +
                "   m.duracaoEmMinutos duracaoEmMinutosMidia, " +
                "   atorPrincipal.id idAtorPrincipal, " +
                "   atorPrincipal.nome nomeAtorPrincipal, " +
                "   atorPrincipal.sobrenome sobrenomeAtorPrincipal, " +
                "   atorPrincipal.dataEstreia dataEstreiaAtorPrincipal, " +
                "   atorCoadjuvante.id idAtorCoadjuvante, " +
                "   atorCoadjuvante.nome nomeAtorCoadjuvante, " +
                "   atorCoadjuvante.sobrenome sobrenomeAtorCoadjuvante, " +
                "   atorCoadjuvante.dataEstreia dataEstreiaAtorCoadjuvante, " +
                "   genero.id idGenero, " +
                "   genero.descricao descricaoGenero, " +
                "   classificacaoEtaria.id idClassificacaoEtaria, " +
                "   classificacaoEtaria.descricao descricaoClassificacaoEtaria, " +
                "   tipo.id idTipo, " +
                "   tipo.descricao descricaoTipo, " +
                "   classificacaoInterna.id idClassificacaoInterna, " +
                "   classificacaoInterna.descricao descricaoClassificacaoInterna " +
                "FROM" +
                "   midia m, " +
                "   ator_atriz atorPrincipal, " +
                "   ator_atriz atorCoadjuvante, " +
                "   genero genero, " +
                "   classificacao_etaria classificacaoEtaria, " +
                "   tipo tipo, " +
                "   classificacao_interna classificacaoInterna " +
                "WHERE" +
                "   m.id = ? AND " +
                "   m.ator_atriz_principal = atorPrincipal.id AND " +
                "   m.ator_atriz_coadjuvante = atorCoadjuvante.id AND " +
                "   m.genero_id = genero.id AND " +
                "   m.classificacao_etaria_id = classificacaoEtaria.id AND " +
                "   m.tipo_id = tipo.id AND " +
                "   m.classificacao_interna_id = classificacaoInterna.id; ");
        
        stmt.setLong(1, id);
        
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            
            midia = new Midia();
            
            AtorAtriz atorPrincipal = new AtorAtriz();
            atorPrincipal.setId(rs.getLong("idAtorPrincipal"));
            atorPrincipal.setNome(rs.getString("nomeAtorPrincipal"));
            atorPrincipal.setSobrenome(rs.getString("sobrenomeAtorPrincipal"));
            atorPrincipal.setDataEstreia(rs.getDate("dataEstreiaAtorPrincipal"));
            
            AtorAtriz atorCoadjuvante = new AtorAtriz();
            atorCoadjuvante.setId(rs.getLong("idAtorCoadjuvante"));
            atorCoadjuvante.setNome(rs.getString("nomeAtorCoadjuvante"));
            atorCoadjuvante.setSobrenome(rs.getString("sobrenomeAtorCoadjuvante"));
            atorCoadjuvante.setDataEstreia(rs.getDate("dataEstreiaAtorCoadjuvante"));
            
            Genero genero = new Genero();
            genero.setId(rs.getLong("idGenero"));
            genero.setDescricao(rs.getString("descricaoGenero"));
            
            ClassificacaoEtaria classificacaoEtaria = new ClassificacaoEtaria();
            classificacaoEtaria.setId(rs.getLong("idClassificacaoEtaria"));
            classificacaoEtaria.setDescricao(rs.getString("descricaoClassificacaoEtaria"));
            
            Tipo tipo = new Tipo();
            tipo.setId(rs.getLong("idTipo"));
            tipo.setDescricao(rs.getString("descricaoTipo"));
            
            ClassificacaoInterna classificacaoInterna = new ClassificacaoInterna();
            classificacaoInterna.setId(rs.getLong("idClassificacaoInterna"));
            classificacaoInterna.setDescricao(rs.getString("descricaoClassificacaoInterna"));
            
            midia.setId(rs.getLong("idMidia"));
            midia.setTitulo(rs.getString("tituloMidia"));
            midia.setAnoLancamento(rs.getString("anoLancamentoMidia"));
            midia.setCodigoBarras(rs.getString("codigoBarrasMidia"));
            midia.setDuracaoEmMinutos(rs.getLong("duracaoEmMinutosMidia"));
            midia.setAtorAtrizPrincipal(atorPrincipal);
            midia.setAtorAtrizCoadjuvante(atorCoadjuvante);
            midia.setGenero(genero);
            midia.setClassificacaoEtaria(classificacaoEtaria);
            midia.setTipo(tipo);
            midia.setClassificacaoInterna(classificacaoInterna);
            
        }
        
        rs.close();
        stmt.close();
        
        return midia;
        
    }
 
}
