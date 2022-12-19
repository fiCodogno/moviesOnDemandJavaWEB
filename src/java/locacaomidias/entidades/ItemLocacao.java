/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.entidades;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 *
 * @author Filipe
 */
public class ItemLocacao {
    
    @NotNull
    private Locacao locacao;
    
    @NotNull
    private Exemplar exemplar;
    
    @NotNull
    @PositiveOrZero
    private BigDecimal valor;

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString(){
        return "ItemLocacao: \n"
               + "  - " + locacao.toString() + "\n"
               + "  - " + exemplar.toString() + "\n"
               + "  - valor = " + valor + "."; 
    }
    
}
