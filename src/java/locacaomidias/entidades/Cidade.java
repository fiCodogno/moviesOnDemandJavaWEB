/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.entidades;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Filipe
 */
public class Cidade {
    
    @NotNull
    private Long id;
    
    @NotNull
    @Size (min = 1, max = 30)
    private String nome;
    
    @NotNull
    private Estado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString(){
        return "Cidade:" + "\n"
               + "    - id = " + id + "\n"
               + "    - nome = " + nome + "\n"
               + "    - " + estado.toString();
    }
    
}
