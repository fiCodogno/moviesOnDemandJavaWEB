/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.entidades;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Filipe
 */
public class Midia {
    
    @NotNull
    private Long id;
    
    @NotNull
    @Size (min = 1, max = 100)
    private String titulo;
    
    @NotNull
    @Size (min = 1, max = 4)
    private String anoLancamento;
    
    @NotNull
    @Pattern( regexp = "^\\d{13}$",
              message = "deve corresponder à 9999999999999" )
    private String codigoBarras;
    
    @NotNull
    private Long duracaoEmMinutos;
    
    @NotNull
    private AtorAtriz atorAtrizPrincipal;
    
    @NotNull
    private AtorAtriz atorAtrizCoadjuvante;
    
    @NotNull
    private Genero genero;
    
    @NotNull
    private ClassificacaoEtaria classificacaoEtaria;
    
    @NotNull
    private Tipo tipo;
    
    @NotNull
    private ClassificacaoInterna classificacaoInterna;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Long getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public void setDuracaoEmMinutos(Long duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public AtorAtriz getAtorAtrizPrincipal() {
        return atorAtrizPrincipal;
    }

    public void setAtorAtrizPrincipal(AtorAtriz atorAtrizPrincipal) {
        this.atorAtrizPrincipal = atorAtrizPrincipal;
    }

    public AtorAtriz getAtorAtrizCoadjuvante() {
        return atorAtrizCoadjuvante;
    }

    public void setAtorAtrizCoadjuvante(AtorAtriz atorAtrizCoadjuvante) {
        this.atorAtrizCoadjuvante = atorAtrizCoadjuvante;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public ClassificacaoEtaria getClassificacaoEtaria() {
        return classificacaoEtaria;
    }

    public void setClassificacaoEtaria(ClassificacaoEtaria classificacaoEtaria) {
        this.classificacaoEtaria = classificacaoEtaria;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public ClassificacaoInterna getClassificacaoInterna() {
        return classificacaoInterna;
    }

    public void setClassificacaoInterna(ClassificacaoInterna classificacaoInterna) {
        this.classificacaoInterna = classificacaoInterna;
    }
    
    @Override
    public String toString(){
        return "Mídia: \n"
               + "  - id = " + id + "\n"
               + "  - titulo = " + titulo + "\n"
               + "  - ano de lancamento = " + anoLancamento + "\n"
               + "  - codigo de barras = " + codigoBarras + "\n"
               + "  - duracao em minutos = " + duracaoEmMinutos + "\n"
               + "  - " + atorAtrizPrincipal.toString() + "\n"
               + "  - " + atorAtrizCoadjuvante.toString() + "\n"
               + "  - " + genero.toString() + "\n"
               + "  - " + classificacaoEtaria.toString() + "\n"
               + "  - " + tipo.toString() + "\n"
               + "  - " + classificacaoInterna.toString();
    }
    
}
