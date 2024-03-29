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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class TransaccionClienteEntity extends BaseEntity implements Serializable
{    
        	
    private static final Logger LOGGER = Logger.getLogger(TransaccionClienteEntity.class.getName());

    
    private Double monto;
    
    private Double tiempo;
    
    private Boolean parcial;
    
    private String tipoDePago;
    
    
    
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ClienteEntity cliente;
    
    @PodamExclude
    @ManyToMany(mappedBy = "transaccionesCliente")
    private List<ProductoEntity> productosCliente = new ArrayList<>();
 
    
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
    
    
    public void setParcial(Boolean pParcial)
    {
        parcial=pParcial;
    }
    
    public Boolean getParcial()
    {
        return parcial;
    }
    
    public ClienteEntity getCliente()
    {
        return cliente;
    }
    
    public void setCliente(ClienteEntity pCliente)
    {
        this.cliente= pCliente;
    }
    
    public void setProductosCliente(List<ProductoEntity> pProductos)
    {
        productosCliente= pProductos;
    }
    
    public List<ProductoEntity> getProductosCliente()
    {
        return productosCliente;
    }
}
