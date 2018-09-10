/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author francisco
 */
@Entity 
//BaseEntity clase madre.
//JPA recompila los datos y crea la respectiva base
public class ProveedorEntity extends BaseEntity implements Serializable
{
    private String nombre;
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorEntity.class.getName());
    
    @PodamExclude
    @OneToMany(mappedBy = "proveedor")
    private List<ProductoEntity> productos = new ArrayList<ProductoEntity>();

    public void setNombre(String pNombre)
    {
        nombre = pNombre;
    }
    public String getNombre()
    {
        return nombre;
    }
    /*public List<ProductoEntity> getProductos()
    {
        return productos;
    }
    
    public void setProductos(List<ProductoEntity> pProductos)
    {
        productos = pProductos;
    }*/
}
