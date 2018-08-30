/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dto.TransaccionProveedorDTO;
import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
//import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * @author jd.florezg1
 */
 @Produces("application/json")
 @Consumes("application/json")
 @RequestScoped
 @Path("transaccionProveedor")
public class TransaccionProveedorResource extends TransaccionProveedorDTO {
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorResource.class.getName());
     /**
      * @return Informacion de la transaccion.
      */
    @GET
    @Path("{id: \\d+}" )
    public TransaccionProveedorDTO obtenerTransaccion(@PathParam("id")Long id)
    {
                  LOGGER.log(Level.INFO, "ProveedorResource getEditorial: input: {0}", id);
      //  ProveedorEntity proveedorEntity = proveedorLogic.getProveedor(id);
     //   if (proveedorEntity == null) {
           // throw new WebApplicationException("El recurso /proveedores/" + id + " no existe.", 404);
        
      //  ProveedorDetailDTO detailDTO = new ProveedorDetailDTO(editorialEntity);
      //  LOGGER.log(Level.INFO, "ProveedorResource getEditorial: output: {0}", detailDTO.toString());
      //  return detailDTO;
      TransaccionProveedorDTO p = new TransaccionProveedorDTO(new TransaccionProveedorEntity());
      p.setId(id);
      p.setMonto(3.0);
      p.setTiempo("13");
      
        System.out.println("co.edu.uniandes.csw.farmacia.resources.ProveedorResource.getProveedor()"+p.getMonto() + p.getTiempo());
      //Este el caso de prueba para verificar que si sirvan las pruebas de Postman
        return p;    
    }
    
    /**
     * Cambia los datos de la transacción por los que llegan por parametro.
     */
    @PUT
    @Path("{id: \\d+}" )
    public TransaccionProveedorDTO actualizarInformacion(@PathParam("id") Long id, TransaccionProveedorDTO tProveedor)
    {
        return tProveedor;
    }
    
    @POST
    @Path("{id: \\d+}")
    public TransaccionProveedorDTO crearTransaccion(TransaccionProveedorDTO transaccion)
    {
        //LOGGER.log(Level.INFO, "EditorialResource createEditorial: input: {0}", transaccion.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        //TransaccionProveedorEntity transaccionEntity = transaccion.toEntity();
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        //TransaccionProveedorDTO nuevaTransaccionDTO = new TransaccionProveedorDTO(transaccionEntity);
        //LOGGER.log(Level.INFO, "EditorialResource createEditorial: output: {0}", nuevaTransaccionDTO.toString());
        return transaccion;
    }
    
}
