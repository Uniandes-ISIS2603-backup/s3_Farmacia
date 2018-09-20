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
    private Integer cantidad;
    private String tipoRegistro;
    //TIPO DE REGISTRO
    /**
     * Relacion con el producto con el que esta asociado
     */
    private ProductoDTO producto;
    
    /**
     * Contructir por defecto
     */
    public RegistroDTO(){
        
    }
    
    /**
     * Contructor apartir de una entidad
     * @param registroEntity 
     */
    public RegistroDTO(RegistroEntity registroEntity){
        if ( registroEntity != null){
            this.id = registroEntity.getId();
            this.cantidad = registroEntity.getCantidad();
            this.tipoRegistro = registroEntity.getTipoRegistro();
            if (registroEntity.getProducto() != null){
                this.producto = new ProductoDTO(registroEntity.getProducto());
            }else{
                this.producto = null;
            }
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
    
    /**
     * Obtiene la cantidad de producto que indica el registro
     * @return 
     */
     public Integer getCantidad(){
        return cantidad;
    }
    
    public void setCantidad(Integer cantidad){
        this.cantidad = cantidad;
    }
    
    /**
     * Obtiene el tipo de registro del registro
     * @return 
     */
    public String getTipoRegistro(){
        return tipoRegistro;
    }
    
    public void setTipoRegistro(String tipo){
        this.tipoRegistro = tipo;
    }
    
    /**
     * Obtiene el prodcito al que esta relacionado el registro
     * @return 
     */
    public ProductoDTO getProducto(){
        return producto;
    }
    
    public void setProducto(ProductoDTO producto){
        this.producto = producto;
    }
    
    /**
     * Metodo para trasformar el DTO a una entidad
     * @return La entidad de este registro
     */
    public RegistroEntity toEntity(){
        RegistroEntity registroEntity = new RegistroEntity();
        registroEntity.setId(this.id);
        registroEntity.setCantidad(this.cantidad);
        registroEntity.setTipoRegistro(this.tipoRegistro);
        if(this.producto != null){
            registroEntity.setProducto(this.producto.toEntity());
        }
        return registroEntity;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
