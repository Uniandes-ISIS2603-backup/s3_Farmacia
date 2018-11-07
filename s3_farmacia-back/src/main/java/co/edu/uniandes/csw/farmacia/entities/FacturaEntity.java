/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author df.machado10
 */
@Entity
public class FacturaEntity extends BaseEntity implements Serializable {
    
    public enum TipoFactura
    {
        REAPROVISIONAMIENTO, DESPACHO     
    }
      
    @PodamExclude
    @OneToOne
    private TransaccionClienteEntity transaccionCliente;
    
    private String Fecha;
    
    private Double precio;
    
    
    private Integer unidades;


    private TipoFactura tipo;
    
    public String getFecha() {
        return Fecha;
    }

    public Double getPrecio() {
        return precio;
    }


    public Integer getUnidades() {
        return unidades;
    }

    public TipoFactura getTipo() {
        return tipo;
    }

    public TransaccionClienteEntity getTransaccionCliente() {
        return transaccionCliente;
    }
    
    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }
    
 
    public void setTipo(TipoFactura tipo) {
        this.tipo = tipo;
    }

    public void setTransaccionCliente(TransaccionClienteEntity transaccionCLiente) {
        this.transaccionCliente = transaccionCLiente;
    }
}
