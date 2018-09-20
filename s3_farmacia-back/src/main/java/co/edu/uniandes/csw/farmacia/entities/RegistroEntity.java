/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author estudiante
 */
@Entity
public class RegistroEntity extends BaseEntity implements Serializable {
    
    public static final String ORDEN_REAPROVISONAMIENTO = "ORDEN_REAPROVISONAMIENTO";
    public static final String TRASLADO_BODEGA = "TRASLADO_BODEGA";
    public static final String DESPACHO_CLIENTE = "DESPACHO_CLIENTE";
    public static final String ROBO = "ROBO";
    public static final String PERDIDA = "PERDIDA";
    public static final String VENCIMIENTO = "VENCIMIENTO";
     
    private Integer cantidad;
    private String tipoRegistro;
    
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ProductoEntity producto;
    
    //FALTA TIPO
    
    /**
     * Retorna la cantidad que indica el registro
     * @return 
     */
    public Integer getCantidad(){
        return cantidad;
    }
    
    /**
     * Modifica la cantidad del registro
     * @param cantidad 
     */
    public void setCantidad(Integer cantidad){
        this.cantidad = cantidad;
    }
    
    /**
     * Obtiene el tipo del registro
     * @return 
     */
    public String getTipoRegistro(){
        return tipoRegistro;
    }
    
    /**
     * Modifica el tipo de registro
     * @param tipo 
     */
    public void setTipoRegistro(String tipo){
        this.tipoRegistro = tipo;
    }
    
    /**
     * Obtiene el producto al que pertenece el registro
     * @return 
     */
    public ProductoEntity getProducto(){
        return producto;
    }
    
    /**
     * Modifica el producto al que pertenece el registro
     * @param productoEntity 
     */
    public void setProducto(ProductoEntity productoEntity){
        this.producto = productoEntity;
    }
    
    
}
