/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.entidades;

import javax.validation.constraints.NotNull;

/**
 *
 * @author Filipe
 */
public class Exemplar {
    
    @NotNull
    private Long codigo_interno;
    
    @NotNull
    private boolean disponivel;
    
    @NotNull
    private Midia midia;

    public Long getCodigo_interno() {
        return codigo_interno;
    }

    public void setCodigo_interno(Long codigo_interno) {
        this.codigo_interno = codigo_interno;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Midia getMidia() {
        return midia;
    }

    public void setMidia(Midia midia) {
        this.midia = midia;
    }
    
    @Override
    public String toString(){
        return "Exemplar: \n"
               + "  - codigo interno = " + codigo_interno + "\n"
               + "  - disponivel? " + disponivel + "\n"
               + "  - " + midia.toString();
    }
    
}
