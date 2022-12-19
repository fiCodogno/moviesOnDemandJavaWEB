/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.entidades;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Filipe
 */
public class AtorAtriz {
    
    @NotNull
    private Long id;
    
    @NotNull
    @Size (min = 1, max = 45)
    private String nome;
    
    @NotNull
    @Size (min = 1, max = 45)
    private String sobrenome;
    
    @NotNull
    private Date dataEstreia;

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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getDataEstreia() {
        return dataEstreia;
    }

    public void setDataEstreia(Date dataEstreia) {
        this.dataEstreia = dataEstreia;
    }
    
    @Override
    public String toString() {
        return "Ator: \n" 
                + " - id = " + id + "\n"
                + " - nome = " + nome + "\n" 
                + " - sobrenome = " + sobrenome + "\n" 
                + " - dataEstreia = " + dataEstreia + "."; 
    }
    
}
