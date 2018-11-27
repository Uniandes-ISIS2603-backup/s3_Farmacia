/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author fj.gonzalez
 */
@Entity
//BaseEntity clase madre.
//JPA recompila los datos y crea la respectiva base
public class ProveedorEntity extends BaseEntity implements Serializable {

    /**
     * Representa el nombre del proveedor
     */
    private String nombre;

    /**
     * Los productos que ofrece el proveedor
     */
    @PodamExclude
    @ManyToMany(mappedBy = "proveedores")
    private List<ProductoEntity> productos = new ArrayList<ProductoEntity>();

    /**
     * Las transacciones que ha ejecutado el proveedor con DrugsHouse
     */
    @PodamExclude
    @OneToMany(mappedBy = "proveedor")
    private List<TransaccionProveedorEntity> transacciones = new ArrayList<TransaccionProveedorEntity>();

    /**
     * Modifica el nombre del proveedor
     * @param pNombre  El nuevo nombre
     */
    public void setNombre(String pNombre) {
        nombre = pNombre;
    }
    /**
     * Retorna el nombre del proveedor
     * @return el nombre del proveedor
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Obtiene los productos del proveedor.
     * @return los producos ofrecidos por el proveedor 
    */
    public List<ProductoEntity> getProductos() {
        return productos;
    }
    /**
     * Reemplaza la lista de productos del proveedor
     * @param pProductos los nuevos productos del proveedor
     */
    public void setProductos(List<ProductoEntity> pProductos) {
        productos = pProductos;
    }
    /**
     * Retorna la lista de transacciones del proveedor
     * @return la lista d etransacciones ejecutadas por el proveedor
     */
    public List<TransaccionProveedorEntity> getTransacciones() {
        return transacciones;
    }
    /**
     * Reemplaza la lista de transacciones.
     * @param pTrans la nueva lista de transacciones
     */
    public void setTransacciones(List<TransaccionProveedorEntity> pTrans) {
        transacciones = pTrans;
    }
}
