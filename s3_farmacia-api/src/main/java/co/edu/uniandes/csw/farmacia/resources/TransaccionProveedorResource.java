/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dto.TransaccionProveedorDTO;
import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.logging.Level;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author jd.florezg1
 */
 @Produces("application/json")
 @Consumes("application/json")
 @RequestScoped
 @Path("transaccionProveedor")
public class TransaccionProveedorResource extends TransaccionProveedorDTO {
    
     /**
      * @return Informacion de la transaccion.
      */
    @GET
    @Path("{id: \\d+}" )
    public TransaccionProveedorDTO obtenerDinero()
    {
         return this;      
    }
    
    /**
     * Cambia los datos de la transacción por los que llegan por parametro.
     */
    @PUT
    @Path("{id: \\d+}" )
    public void actualizarInformacion(TransaccionProveedorDTO transaccion)
    {
        this.monto = transaccion.getMonto();
        this.tiempo = transaccion.darTiempo();
    }
    
    @POST
    @Path("{id: \\d+}")
    public TransaccionProveedorDTO crearTransaccion(TransaccionProveedorDTO transaccion)
    {
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: input: {0}", transaccion.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        TransaccionProveedorEntity transaccionEntity = transaccion.toEntity();
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        TransaccionProveedorDTO nuevaTransaccionDTO = new TransaccionProveedorDTO(transaccionEntity);
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: output: {0}", nuevaTransaccionDTO.toString());
        return nuevaTransaccionDTO;
    }
    
}
