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
    
    public enum TIPO_FACTURA
    {
        REAPROVISIONAMIENTO, DESPACHO     
    }
      
    @PodamExclude
    @OneToOne
    private TransaccionClienteEntity transaccionCliente;
    
    private String fecha;
    
    private Double precio;
        
    private Integer unidades;
    
    private TIPO_FACTURA tipo; 
    
    
    public String getFecha() {
        return fecha;
    }

    public Double getPrecio() {
        return precio;
    }


    public Integer getUnidades() {
        return unidades;
    }

    public TIPO_FACTURA getTipo() {
        return tipo;
    }

    public TransaccionClienteEntity getTransaccionCliente() {
        return transaccionCliente;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }
    
 
    public void setTipo(TIPO_FACTURA tipo) {
        this.tipo = tipo;
    }

    public void setTransaccionCliente(TransaccionClienteEntity transaccionClienteEntity) {
        this.transaccionCliente = transaccionClienteEntity;
    }
}