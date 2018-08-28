/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;


/**
 *
 * @author ra.ariasr
 */
public class TransaccionProvedorDTO
{
    private double monto;
    
    private String tiempo;
    
    private Long id;
    
    public TransaccionProvedorDTO() 
    {
        
    }
    
    public  TransaccionProvedorDTO(transaccionProvedorEntity trans)
    {
        if (trans != null) {
            this.id = trans.getId();
            this.tiempo = trans.getTiempo();
            this.monto = trans.getMonto();
        }
    }
    public transaccionProvedorEntity toEntity()
    {
        
    }
}
