/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class TransaccionClienteEntity extends BaseEntity implements Serializable
{    
    private Long id;
    
    private Double monto;
    
    private Double tiempo;
    
    private boolean parcial;
    
    private String tipoDePago;

    public void setMonto(String pTipoDePago)
    {
        tipoDePago=pTipoDePago;
    }
    public String getMonto()
    {
        return tipoDePago;
    }
    public void setMonto(Double pMonto)
    {
        monto=pMonto;
    }
    public Double GetMonto()
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
    
    public void setTipoDePago(String pTipoDePago)
    {
        tipoDePago=pTipoDePago;
    }
    
    public String getTipoDePago()
    {
        return tipoDePago;
    }
   public void setId(Long pID)
    {
        id=pID;
    }
    
    public long getId()
    {
        return id;
    }
    
    public void setParcial(boolean pParcial)
    {
        parcial=pParcial;
    }
    
    public boolean getParcial()
    {
        return parcial;
    }
}
