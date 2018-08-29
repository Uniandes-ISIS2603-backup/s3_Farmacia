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
    
    public static final String ORDEN_REAPROVISONAMIENTO = "ORDEN_REAPROVISONAMIENTO";
    public static final String TRASLADO_BODEGA = "TRASLADO_BODEGA";
    public static final String DESPACHO_CLIENTE = "DESPACHO_CLIENTE";
    public static final String ROBO = "ROBO";
    public static final String PERDIDA = "PERDIDA";
    public static final String VENCIMIENTO = "VENCIMIENTO";
    
    private Long id;
    private int cantidad;
    private String tipoRegistro;
    //TIPO DE REGISTRO
    
    public RegistroDTO(){
        
    }
    
    public RegistroDTO(RegistroEntity registroEntity){
        if ( registroEntity != null){
            this.id = registroEntity.getId();
            this.cantidad = registroEntity.getCantidad();
            this.tipoRegistro = registroEntity.getTipoRegistro();
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
    
    public String getTipoRegistro(){
        return tipoRegistro;
    }
    
    public void setTipoRegistro(String tipo){
        this.tipoRegistro = tipo;
    }
    
    public RegistroEntity toEntity(){
        RegistroEntity registroEntity = new RegistroEntity();
        registroEntity.setId(this.id);
        registroEntity.setCantidad(this.cantidad);
        registroEntity.setTipoRegistro(this.tipoRegistro);
        return registroEntity;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
