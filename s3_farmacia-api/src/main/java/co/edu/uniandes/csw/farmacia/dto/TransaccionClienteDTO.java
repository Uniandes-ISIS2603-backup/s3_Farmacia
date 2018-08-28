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
    
    private double monto;
    
    private double tiempo;
    
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
            this.parcial= tran.getParcial;
        }
    }
    public TransaccionClienteEntity toEntity()
    {
        return null;
    }
     
    
}
