/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dto;

import co.edu.uniandes.csw.farmacia.entities.TransaccionClienteEntity;
import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
*   {
 *      "id": number,
 *      "nombre": string,
 *       "apellido": string,
 *      "ciudad" : string,
 *      "direccionEnvio" : string,
 *      "cedula" : number,
 *      "transaccionesCliente": [{@link TransaccionClienteDTO}]
 *   }
 **/



 
public class ClienteDetailDTO extends ClienteDTO implements Serializable {
    
    private List<TransaccionClienteDTO> transaccionesCliente;
    
    public ClienteDetailDTO()
    {
        super();
        transaccionesCliente = new ArrayList<>();
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param clienteEntity La entidad del cliente para transformar a DTO.
     */
    public ClienteDetailDTO(ClienteEntity clienteEntity) {
        super(clienteEntity);
             transaccionesCliente = new ArrayList<>();
            if (clienteEntity.getTransaccionesCliente() != null) {
                for (TransaccionClienteEntity entityTransaccion : clienteEntity.getTransaccionesCliente()) {
                    transaccionesCliente.add(new TransaccionClienteDTO(entityTransaccion));
                }
            }
        
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO del cliente para transformar a Entity
     */
    @Override
    public ClienteEntity toEntity() {
        ClienteEntity clienteEntity = super.toEntity();
        if (transaccionesCliente != null) {
            
            List<TransaccionClienteEntity> transaccionesEntity = new ArrayList<>();
            for (TransaccionClienteDTO dtoTransaccion: transaccionesCliente)  {
                transaccionesEntity.add(dtoTransaccion.toEntity());
            }
            clienteEntity.setTransaccionesCliente(transaccionesEntity);
            
        }
        return clienteEntity;
    }
    
    /**
     * Devuelve la lista de transaccionesCLiente del cliente.
     *
     * @return las transacciones
     */
    public List<TransaccionClienteDTO> getTransaccionesCliente() {
        return transaccionesCliente;
    }
    
    /**
     * Modifica la lista de transaccionesCliente del cliente.
     *
     * @param transaccionesCliente las transacciones para poner
     */
    public void setTransaccionesCliente(List<TransaccionClienteDTO> transaccionesCliente) {
        this.transaccionesCliente = transaccionesCliente;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    
    
}
