/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;

import co.edu.uniandes.csw.farmacia.entities.FacturaEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class FacturaDTO {

    
    public enum TipoFactura{
        REAPROVISIONAMIENTO, DESPACHO     
    }
    
    private Long id;
    
    private String fecha;
    
    private Double precio;
        
    private Integer unidades;
    
    private TipoFactura tipo; 
    
    public FacturaDTO(){
        
    }
    
    public FacturaDTO(FacturaEntity facturaEntity){
        
        if(facturaEntity != null){
            
            this.id = facturaEntity.getId();
            this.fecha = facturaEntity.getFecha();
            this.precio = facturaEntity.getPrecio();
            this.unidades = facturaEntity.getUnidades();
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

    public TipoFactura getTipo() {
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

    public void setTipo(TipoFactura tipo) {
        this.tipo = tipo;
    }
    
    public FacturaEntity toEntity(){
        
        FacturaEntity facturaEntity = new FacturaEntity();
        facturaEntity.setId(this.id);
        facturaEntity.setFecha(this.fecha);
        facturaEntity.setPrecio(this.precio);
        facturaEntity.setUnidades(this.unidades);
        
        return facturaEntity;      
    }
  
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
   
}
