/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;
import co.edu.uniandes.csw.farmacia.entities.TransaccionClienteEntity;

/**
 *
 * @author estudiante
 */
public class TransaccionClienteDTO
{
    private Long id;
    
    private Double monto;
    
    private Double tiempo;
    
    private boolean parcial;
    
    private String tipoDePago;

    public TransaccionClienteDTO() 
    {
        
    }
    
    public  TransaccionClienteDTO(TransaccionClienteEntity tran)
    {
        if (tran != null) 
        {
            this.id = tran.getId();
            this.tiempo= tran.getTiempo();
            this.monto= tran.getMonto();
            this.tipoDePago= tran.getTipoDePago();
            this.parcial= tran.getParcial();
            this.tipoDePago=tran.getTipoDePago();
           
        }
    }
    public TransaccionClienteEntity toEntity()
    {
        TransaccionClienteEntity transaccionEntity = new TransaccionClienteEntity();
        transaccionEntity.setId(this.id);
        transaccionEntity.setMonto(this.monto);
        transaccionEntity.setParcial(this.parcial);
        transaccionEntity.setTiempo(this.tiempo);
        transaccionEntity.setTipoDePago(this.tipoDePago);
        return transaccionEntity;
    }
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long pId)
    {
        id=pId;
    }
        
    public Double getMonto()
    {
        return monto;
    }
    
    public void setMonto(Double pMonto)
    {
        monto=pMonto;
    }
    
    public Double getTiempo()
    {
        return tiempo;
    }
    public void setTiempo(Double pTiempo)
    {
        tiempo=pTiempo;
    }
    
    public boolean getParcial()
    {
     return parcial;   
    }
    
    public void setParcial(boolean pParcial)
    {
        parcial=pParcial;
    }
    public String getTipoDePago()
    {
        return tipoDePago;
    }
    public void setTipoDePago(String pTipoDePago)
    {
        tipoDePago=pTipoDePago;
    }
}
