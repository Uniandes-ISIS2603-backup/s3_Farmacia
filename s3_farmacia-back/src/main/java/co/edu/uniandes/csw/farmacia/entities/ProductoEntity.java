/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */

@Entity
public class ProductoEntity extends BaseEntity implements Serializable {
    
    
       public ProductoEntity(){
       //
       }
    
    
    
    
    public enum TipoProducto {
        SUMINISTRO, TERMINADO
    }
    
    @PodamExclude
    @OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE, 
            orphanRemoval = true )
    private List<RegistroEntity> registros = new ArrayList<RegistroEntity>();
    
    @PodamExclude
    @ManyToMany
    private List<ProveedorEntity> proveedores;
    
    @PodamExclude
    @ManyToMany
    private List<TransaccionClienteEntity> transaccionesCliente;
    
    @PodamExclude
    @ManyToMany
    private List<TransaccionProveedorEntity> transaccionesProveedor;

    public List<TransaccionProveedorEntity> getTransaccionProveedor() {
        return transaccionesProveedor;
    }

    public void setTransaccionProveedor(List<TransaccionProveedorEntity> transaccionProveedor) {
        this.transaccionesProveedor = transaccionProveedor;
    }

    public List<TransaccionClienteEntity> getTransaccionesCliente() {
        return transaccionesCliente;
    }

    public void setTransaccionesCliente(
            List<TransaccionClienteEntity> transaccionesCliente) {
        this.transaccionesCliente = transaccionesCliente;
    }

    public List<ProveedorEntity> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<ProveedorEntity> proveedor) {
        this.proveedores = proveedor;
    }

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
    @Temporal(TemporalType.DATE)
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
    
    public List<RegistroEntity> getRegistros(){
        return registros;
    }
    
    public void setRegistros(List<RegistroEntity> registros){
        this.registros = registros;
    }
}

