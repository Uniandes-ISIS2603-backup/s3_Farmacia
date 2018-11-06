/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import java.util.Date;
import java.io.Serializable;
/**
 *
 * @author estudiante
 */
public class ProductoDTO implements Serializable {
    
    public ProductoDTO() {
        
    }
    
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public ProductoDTO(ProductoEntity producto) {
        if(producto != null) {
            System.out.println("ProductoEntity has been built");
            this.id = producto.getId();
            this.fecha = producto.getFecha();
            this.precio = producto.getPrecio();
            this.tipoProducto = producto.getTipoProducto();
            this.unidadesDisponibles = producto.getUnidadesDisponibles();
            this.fotos = producto.getFotos();
            this.videos = producto.getVideos();
            this.perecedero = producto.getPerecedero();
            this.cantidad = producto.getCantidad();
            this.nombre = producto.getNombre();
        }
    }
    
    public ProductoEntity toEntity() {
        ProductoEntity producto = new ProductoEntity();
        producto.setFecha(fecha);
        producto.setId(id);
        producto.setPrecio(precio);
        producto.setFotos(fotos);
        producto.setVideos(videos);
        producto.setTipoProducto(tipoProducto);
        producto.setUnidadesDisponibles(unidadesDisponibles);
        producto.setPerecedero(perecedero);
        producto.setCantidad(cantidad);
        producto.setNombre(nombre);
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

    public void setPerecedero(Boolean perecedero) {
        this.perecedero = perecedero;
    }

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
    
    @Override
    public String toString()
    {
        //Ctrl+shift+i importar.
        return super.toString();
    }
}
