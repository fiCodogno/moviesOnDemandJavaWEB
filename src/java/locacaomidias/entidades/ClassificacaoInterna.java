/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.entidades;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 *
 * @author Filipe
 */
public class ClassificacaoInterna {
    
    @NotNull
    private Long id;
    
    @NotNull
    @Size (min = 1, max = 45)
    private String descricao;
    
    @NotNull
    @PositiveOrZero
    private BigDecimal valorAluguel;

    public BigDecimal getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(BigDecimal valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public String toString() {
        return "Classificacao Interna: \n"
               + "   - id = " + id + "\n"
               + "   - descricao = " + descricao + "\n"
               + "   - valor do aluguel = " + valorAluguel + ".";
    }
    
}
