/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;

import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import co.edu.uniandes.csw.farmacia.entities.FacturaEntity;
import co.edu.uniandes.csw.farmacia.entities.TransaccionClienteEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author df.machado10
 */
public class FacturaDetailDTO extends FacturaDTO implements Serializable{
    
    private TransaccionClienteDTO transaccionCliente;
    
    
    public FacturaDetailDTO(){
        
    }
    
    public FacturaDetailDTO(FacturaEntity facturaEntity) {
        super(facturaEntity);
        if (facturaEntity != null) {
            
            transaccionCliente = new TransaccionClienteDTO();
            facturaEntity.setTransaccionCliente(transaccionCliente.toEntity());
            
        }
    }
    
    public TransaccionClienteDTO getTransaccionCliente(){
        return transaccionCliente;
    }
    
    @Override
    public FacturaEntity toEntity() {
        FacturaEntity facturaEntity = super.toEntity();
        if (transaccionCliente != null) {
            TransaccionClienteEntity transaccionEntity = transaccionCliente.toEntity();

            facturaEntity.setTransaccionCliente(transaccionEntity);
        }
        return facturaEntity;
    }
}
