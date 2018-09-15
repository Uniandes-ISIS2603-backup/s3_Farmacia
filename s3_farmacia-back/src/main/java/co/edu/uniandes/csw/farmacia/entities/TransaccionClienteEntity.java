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
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class TransaccionClienteEntity extends BaseEntity implements Serializable
{    
    
    private Double monto;
    
    private Double tiempo;
    
    private boolean parcial;
    
    private String tipoDePago;
    
    private List<ProductoEntity> productos;
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;
     private static final Logger LOGGER = Logger.getLogger(TransaccionClienteEntity.class.getName());
    public void setTipoDePago(String pTipoDePago)
    {
        tipoDePago=pTipoDePago;
    }
    public String getTipoDePago()
    {
        return tipoDePago;
    }
    public void setMonto(Double pMonto)
    {
        monto=pMonto;
    }
    public Double getMonto()
    {
       return monto;
    }
    
    public void setTiempo(Double pTiempo)
    {
        tiempo=pTiempo;
    }
    
    public Double getTiempo()
    {
        return tiempo;
    }
    
    
    public void setParcial(boolean pParcial)
    {
        parcial=pParcial;
    }
    
    public boolean getParcial()
    {
        return parcial;
    }
    
    public ClienteEntity getCliente()
    {
        return cliente;
    }
    
    public void setCliente(ClienteEntity pCliente)
    {
        cliente= pCliente;
    }
    
    public void setProductos(List<ProductoEntity> pProductos)
    {
       productos= pProductos;
    }
    public List<ProductoEntity> getProductos()
    {
       return productos;
    }
    
}
