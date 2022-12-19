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
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.Genero;
import locacaomidias.entidades.Midia;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

/**
 *
 * @author Filipe
 */
public class ExemplarDAO extends DAO<Exemplar> {

    public ExemplarDAO() throws SQLException {

    }

    @Override
    public void salvar(Exemplar obj) throws SQLException {

        PreparedStatement stmt = getConnecction().prepareStatement(
                "INSERT INTO "
                + "exemplar( disponivel, midia_id ) "
                + "VALUES( ?, ? );",
                new String[]{"codigo_interno"});

        stmt.setBoolean(1, obj.isDisponivel());
        stmt.setLong(2, obj.getMidia().getId());

        stmt.executeUpdate();
        obj.setCodigo_interno(Utils.getChavePrimariaAposInsercao(stmt, "codigo_interno"));
        stmt.close();

    }

    @Override
    public void atualizar(Exemplar obj) throws SQLException {

        PreparedStatement stmt = getConnecction().prepareStatement(
                "UPDATE exemplar "
                + "SET"
                + "   disponivel = ?, "
                + "   midia_id = ? "
                + "WHERE"
                + "   codigo_interno = ?;");

        stmt.setBoolean(1, obj.isDisponivel());
        stmt.setLong(2, obj.getMidia().getId());
        stmt.setLong(3, obj.getCodigo_interno());

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public void excluir(Exemplar obj) throws SQLException {

        PreparedStatement stmt = getConnecction().prepareStatement(
                "DELETE FROM exemplar "
                + "WHERE"
                + "   codigo_interno = ?;");

        stmt.setLong(1, obj.getCodigo_interno());

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public List<Exemplar> listarTodos() throws SQLException {

        List<Exemplar> lista = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT"
                + "   e.codigo_interno codigo_internoExemplar, "
                + "   e.disponivel disponivelExemplar, "
                + "   m.id idMidia, "
                + "   m.titulo tituloMidia, "
                + "   m.anoLancamento anoLancamentoMidia, "
                + "   m.codigoBarras codigoBarrasMidia, "
                + "   m.duracaoEmMinutos duracaoEmMinutosMidia, "
                + "   atorPrincipal.id idAtorPrincipal, "
                + "   atorPrincipal.nome nomeAtorPrincipal, "
                + "   atorPrincipal.sobrenome sobrenomeAtorPrincipal, "
                + "   atorPrincipal.dataEstreia dataEstreiaAtorPrincipal, "
                + "   atorCoadjuvante.id idAtorCoadjuvante, "
                + "   atorCoadjuvante.nome nomeAtorCoadjuvante, "
                + "   atorCoadjuvante.sobrenome sobrenomeAtorCoadjuvante, "
                + "   atorCoadjuvante.dataEstreia dataEstreiaAtorCoadjuvante, "
                + "   genero.id idGenero, "
                + "   genero.descricao descricaoGenero, "
                + "   classificacaoEtaria.id idClassificacaoEtaria, "
                + "   classificacaoEtaria.descricao descricaoClassificacaoEtaria, "
                + "   tipo.id idTipo, "
                + "   tipo.descricao descricaoTipo, "
                + "   classificacaoInterna.id idClassificacaoInterna, "
                + "   classificacaoInterna.descricao descricaoClassificacaoInterna, "
                + "   classificacaoInterna.valorAluguel valorAluguelClassificacaoInterna "
                + "FROM"
                + "   exemplar e, "
                + "   midia m, "
                + "   ator_atriz atorPrincipal, "
                + "   ator_atriz atorCoadjuvante, "
                + "   genero genero, "
                + "   classificacao_etaria classificacaoEtaria, "
                + "   tipo tipo, "
                + "   classificacao_interna classificacaoInterna "
                + "WHERE"
                + "   e.midia_id = m.id AND "
                + "   m.ator_atriz_principal = atorPrincipal.id AND "
                + "   m.ator_atriz_coadjuvante = atorCoadjuvante.id AND "
                + "   m.genero_id = genero.id AND "
                + "   m.classificacao_etaria_id = classificacaoEtaria.id AND "
                + "   m.tipo_id = tipo.id AND "
                + "   m.classificacao_interna_id = classificacaoInterna.id "
                + "ORDER BY e.codigo_interno;");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Exemplar exemplar = new Exemplar();

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

            Midia midia = new Midia();
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

            exemplar.setCodigo_interno(rs.getLong("codigo_internoExemplar"));
            exemplar.setDisponivel(rs.getBoolean("disponivelExemplar"));
            exemplar.setMidia(midia);

            lista.add(exemplar);
        }

        rs.close();
        stmt.close();

        return lista;

    }

    @Override
    public Exemplar obterPorId(Long id) throws SQLException {

        Exemplar exemplar = null;

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT"
                + "   e.codigo_interno codigo_internoExemplar, "
                + "   e.disponivel disponivelExemplar, "
                + "   m.id idMidia, "
                + "   m.titulo tituloMidia, "
                + "   m.anoLancamento anoLancamentoMidia, "
                + "   m.codigoBarras codigoBarrasMidia, "
                + "   m.duracaoEmMinutos duracaoEmMinutosMidia, "
                + "   atorPrincipal.id idAtorPrincipal, "
                + "   atorPrincipal.nome nomeAtorPrincipal, "
                + "   atorPrincipal.sobrenome sobrenomeAtorPrincipal, "
                + "   atorPrincipal.dataEstreia dataEstreiaAtorPrincipal, "
                + "   atorCoadjuvante.id idAtorCoadjuvante, "
                + "   atorCoadjuvante.nome nomeAtorCoadjuvante, "
                + "   atorCoadjuvante.sobrenome sobrenomeAtorCoadjuvante, "
                + "   atorCoadjuvante.dataEstreia dataEstreiaAtorCoadjuvante, "
                + "   genero.id idGenero, "
                + "   genero.descricao descricaoGenero, "
                + "   classificacaoEtaria.id idClassificacaoEtaria, "
                + "   classificacaoEtaria.descricao descricaoClassificacaoEtaria, "
                + "   tipo.id idTipo, "
                + "   tipo.descricao descricaoTipo, "
                + "   classificacaoInterna.id idClassificacaoInterna, "
                + "   classificacaoInterna.descricao descricaoClassificacaoInterna, "
                + "   classificacaoInterna.valorAluguel valorAluguelClassificacaoInterna "
                + "FROM"
                + "   exemplar e, "
                + "   midia m, "
                + "   ator_atriz atorPrincipal, "
                + "   ator_atriz atorCoadjuvante, "
                + "   genero genero, "
                + "   classificacao_etaria classificacaoEtaria, "
                + "   tipo tipo, "
                + "   classificacao_interna classificacaoInterna "
                + "WHERE"
                + "   e.codigo_interno = ? AND "
                + "   e.midia_id = m.id AND "
                + "   m.ator_atriz_principal = atorPrincipal.id AND "
                + "   m.ator_atriz_coadjuvante = atorCoadjuvante.id AND "
                + "   m.genero_id = genero.id AND "
                + "   m.classificacao_etaria_id = classificacaoEtaria.id AND "
                + "   m.tipo_id = tipo.id AND "
                + "   m.classificacao_interna_id = classificacaoInterna.id;");

        stmt.setLong(1, id);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            exemplar = new Exemplar();

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

            Midia midia = new Midia();
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

            exemplar.setCodigo_interno(rs.getLong("codigo_internoExemplar"));
            exemplar.setDisponivel(rs.getBoolean("disponivelExemplar"));
            exemplar.setMidia(midia);

        }

        rs.close();
        stmt.close();

        return exemplar;

    }

    public Exemplar visualizar(Long id) throws SQLException {

        Exemplar exemplar = null;

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT"
                + "   e.codigo_interno codigo_internoExemplar, "
                + "   e.disponivel disponivelExemplar, "
                + "   m.titulo tituloMidia, "
                + "   t.descricao descricaoTipo, "
                + "   classificacaoInterna.valorAluguel valorAluguelClassificacaoInterna "
                + "FROM"
                + "   exemplar e, "
                + "   midia m, "
                + "   tipo t, "
                + "   classificacao_interna classificacaoInterna "
                + "WHERE"
                + "   e.codigo_interno = ? AND "
                + "   e.midia_id = m.id AND "
                + "   m.tipo_id = t.id AND "
                + "   m.classificacao_interna_id = classificacaoInterna.id;");

        stmt.setLong(1, id);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            exemplar = new Exemplar();

            Tipo tipo = new Tipo();
            tipo.setDescricao(rs.getString("descricaoTipo"));

            ClassificacaoInterna classificacaoInterna = new ClassificacaoInterna();
            classificacaoInterna.setValorAluguel(rs.getBigDecimal("valorAluguelClassificacaoInterna"));

            Midia midia = new Midia();
            midia.setTitulo(rs.getString("tituloMidia"));
            midia.setTipo(tipo);
            midia.setClassificacaoInterna(classificacaoInterna);
            
            exemplar.setCodigo_interno(rs.getLong("codigo_internoExemplar"));
            exemplar.setMidia(midia);
            exemplar.setDisponivel(rs.getBoolean("disponivelExemplar"));
        }

        rs.close();
        stmt.close();

        return exemplar;
    }

    public void atualizarDisponibilidade(Exemplar obj) throws SQLException {

        PreparedStatement stmt = getConnecction().prepareStatement(
                "UPDATE exemplar "
                + "SET"
                + "   disponivel = ? "
                + "WHERE"
                + "   codigo_interno = ?;");

        stmt.setBoolean(1, obj.isDisponivel());
        stmt.setLong(2, obj.getCodigo_interno());

        stmt.executeUpdate();
        stmt.close();

    }

}
