/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author estudiante
 */

@Entity
public class ProductoEntity extends BaseEntity implements Serializable {
    
    public enum TipoProducto {
        Suministro, Terminado
    }
    
   /* public class PhotoType extends BaseEntity implements Serializable {
        public String url;

        public PhotoType(){}
        
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        
    }
    */
    public ProductoEntity(){}
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorEntity.class.getName());
    
    
    private Boolean perecedero;

    //setter
    public void setPerecedero(Boolean perecedero) {
        this.perecedero = perecedero;
    }

    //getter
    public Boolean getPerecedero() {
        return perecedero;
    }
    
    private TipoProducto tipoProducto;

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
    
    private Double precio;

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    private Integer cantidad;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    private Date fecha;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    private Integer unidadesDisponibles;

    public Integer getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(Integer unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }
    
    private String[] fotos;

    public String[] getFotos() {
        return fotos;
    }

    public void setFotos(String[] fotos) {
        this.fotos = fotos;
    }
    
    private String[] videos;

    public String[] getVideos() {
        return videos;
    }

    public void setVideos(String[] videos) {
        this.videos = videos;
    }
    
    
}
