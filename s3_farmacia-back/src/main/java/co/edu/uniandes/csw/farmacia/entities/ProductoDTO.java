/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author estudiante
 */
public class ProductoDTO extends BaseEntity implements Serializable {
    
    private enum TipoProducto {
        Suministro, Terminado
    }
    
    public class PhotoType extends BaseEntity {
        public String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        
        
    }
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorEntity.class.getName());
    
    public Boolean perecedero;

    //setter
    public void setPerecedero(Boolean perecedero) {
        this.perecedero = perecedero;
    }

    //getter
    public Boolean getPerecedero() {
        return perecedero;
    }
    
    public TipoProducto tipoProducto;

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
    
    public Double precio;

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    public Integer cantidad;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    public Date fecha;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public Integer unidadesDisponibles;

    public Integer getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(Integer unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }
    
    public PhotoType[] fotos;

    public PhotoType[] getFotos() {
        return fotos;
    }

    public void setFotos(PhotoType[] fotos) {
        this.fotos = fotos;
    }
    
    public PhotoType[] videos;

    public PhotoType[] getVideos() {
        return videos;
    }

    public void setVideos(PhotoType[] videos) {
        this.videos = videos;
    }
    
    
}
