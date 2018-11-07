/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;

import co.edu.uniandes.csw.farmacia.entities.FacturaEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class FacturaDTO implements Serializable  {

    
    private Long id;
    
    private String fecha;
    
    private Double precio;
        
    private Integer unidades;
    
private FacturaEntity.TipoFactura tipo;     
    private TransaccionClienteDTO transaccionCliente;
    
    public FacturaDTO(){
        
    }
    
    public FacturaDTO(FacturaEntity facturaEntity){
        
        if(facturaEntity != null){
            
            this.id = facturaEntity.getId();
            this.fecha = facturaEntity.getFecha();
            this.precio = facturaEntity.getPrecio();
            this.unidades = facturaEntity.getUnidades(); 
            this.tipo = facturaEntity.getTipo();
            if(facturaEntity.getTransaccionCliente() != null){
                this.transaccionCliente = new TransaccionClienteDTO(facturaEntity.getTransaccionCliente());
            }
            else
            {
                facturaEntity.setTransaccionCliente(null);
            }
            
        }
    }
    
    
    public Long getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public Double getPrecio() {
        return precio;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public FacturaEntity.TipoFactura getTipo() {
        return tipo;
    }
    

    
    public void setId(Long id) {
        this.id = id;
    }

    public void setFecha(String Fecha) {
        this.fecha = Fecha;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public void setTipo(FacturaEntity.TipoFactura tipo) {
        this.tipo = tipo;
    }
    
    public void setTransaccionCliente(TransaccionClienteDTO transaccionCliente)
    {
        this.transaccionCliente = transaccionCliente;
    }
    public TransaccionClienteDTO getTransaccionCliente()
    {
        return transaccionCliente;
    }
    
    public FacturaEntity toEntity(){
        
        FacturaEntity facturaEntity = new FacturaEntity();
        facturaEntity.setId(this.id);
        facturaEntity.setFecha(this.fecha);
        facturaEntity.setPrecio(this.precio);
        facturaEntity.setUnidades(this.unidades);
        facturaEntity.setTipo(this.tipo);
        if(transaccionCliente != null)
        {
           facturaEntity.setTransaccionCliente(transaccionCliente.toEntity());
        }
        return facturaEntity;      
    }
  
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
   
}