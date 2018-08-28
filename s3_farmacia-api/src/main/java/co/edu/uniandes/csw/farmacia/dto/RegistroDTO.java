/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;
import co.edu.uniandes.csw.farmacia.entities.RegistroEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class RegistroDTO implements Serializable{
    
    private Long id;
    private int cantidad;
    //TIPO DE REGISTRO
    
    public RegistroDTO(){
        
    }
    
    public RegistroDTO(RegistroEntity registroEntity){
        if ( registroEntity != null){
            this.id = registroEntity.getId();
            this.cantidad = registroEntity.getCantidad();
        }
    }
    
     /**
     * Devuelve el ID de la editorial.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el ID de la editorial.
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
     public int getCantidad(){
        return cantidad;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
    public RegistroEntity toEntity(){
        RegistroEntity registroEntity = new RegistroEntity();
        registroEntity.setId(this.id);
        registroEntity.setCantidad(this.cantidad);
        return registroEntity;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
