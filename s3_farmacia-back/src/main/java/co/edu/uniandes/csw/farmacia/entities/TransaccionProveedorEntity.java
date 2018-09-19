/*/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *Clase que contiene la entidad de transaccionProvedor
 * @author jd.florezg1
 */
@Entity
public class TransaccionProveedorEntity extends BaseEntity implements Serializable
{
    
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ProveedorEntity proveedor;
    
    @PodamExclude
    @OneToMany
    private List<ProductoEntity> productos = new ArrayList<>();
    
    /**
     * atributo que hace referencia al timepo empleado en la transaccion
     */
    private String tiempo;
    
    /**
     * atributo que hace referencua al monto empleado en la transaccion
     */
    private Double monto;
    
    private static final Logger LOGGER = Logger.getLogger(TransaccionProveedorEntity.class.getName());
    
    
    
    public void setTiempo(String pTiempo)
    {
        tiempo= pTiempo;
    }
    public String getTiempo()
    {
        return tiempo;
    }
    
    public void setMonto(Double pMonto)
    {
        monto= pMonto;
    }
    
    public Double getMonto()
    {
        return monto;
    }
    
    public ProveedorEntity getProveedor()
    {
        return proveedor;
    }
    
    public void setProveedor(ProveedorEntity proveedorEntity)
    {
        this.proveedor = proveedorEntity;
    }
    
    public List<ProductoEntity> getProductos()
    {
        return productos;
    }
    public void setProductos(List<ProductoEntity> lista)
    {
        productos = lista;
    }
    
}
