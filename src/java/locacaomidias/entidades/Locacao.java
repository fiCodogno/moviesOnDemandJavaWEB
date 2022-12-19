/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaomidias.entidades;

import java.sql.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Filipe
 */
public class Locacao {
    
    @NotNull
    private Long id;
    
    @NotNull
    private Date dataInicio;
    
    @NotNull
    private Date dataFim;
    
    @NotNull
    private boolean cancelada;
    
    @NotNull
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public String toString(){
        return "Locação: \n"
               + "  - id = " + id + "\n"
               + "  - data de inicio = " + dataInicio + "\n"
               + "  - data de fim = " + dataFim + "\n"
               + "  - cancelada? " + cancelada + "\n"
               + "  - " + cliente.toString();
    }
    
}
