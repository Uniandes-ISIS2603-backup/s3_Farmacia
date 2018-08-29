/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class ProductoDTO implements Serializable {
    
    public ProductoDTO() {
        
    }
    
    public ProductoDTO(ProductoEntity producto) {
        if(producto != null) {
            System.err.println("ProductoEntity has been built");
            this.id = producto.getId();
            this.fecha = producto.getFecha();
            this.precio = producto.getPrecio();
            this.tipoProducto = producto.getTipoProducto();
            this.unidadesDisponibles = producto.getUnidadesDisponibles();
            this.fotos = producto.getFotos();
            this.videos = producto.getVideos();
            this.perecedero = producto.getPerecedero();
            this.cantidad = producto.getCantidad();
        }
    }
    
    public ProductoEntity toEntity() {
        ProductoEntity producto = new FacturaEntity();
        producto.setFecha(fecha);
        producto.setId(id);
        producto.setPrecio(precio);
        producto.setFotos(fotos);
        producto.setVideos(videos);
        producto.setTipoProducto(tipoProducto);
        producto.setUnidadesDisponibles(unidadesDisponibles);
        producto.setPerecedero(perecedero);
        producto.setCantidad(cantidad);
        return producto;
    }
    
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    private Boolean perecedero;

    //setter
    public void setPerecedero(Boolean perecedero) {
        this.perecedero = perecedero;
    }

    //getter
    public Boolean getPerecedero() {
        return perecedero;
    }
    
    private ProductoEntity.TipoProducto tipoProducto;

    public ProductoEntity.TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(ProductoEntity.TipoProducto tipoProducto) {
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
    
    private ProductoEntity.PhotoType[] fotos;

    public ProductoEntity.PhotoType[] getFotos() {
        return fotos;
    }

    public void setFotos(ProductoEntity.PhotoType[] fotos) {
        this.fotos = fotos;
    }
    
    private ProductoEntity.PhotoType[] videos;

    public ProductoEntity.PhotoType[] getVideos() {
        return videos;
    }

    public void setVideos(ProductoEntity.PhotoType[] videos) {
        this.videos = videos;
    }
    
          @Override
    public String toString()
    {
        //Ctrl+shift+i importar.
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
