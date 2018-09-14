/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;


import co.edu.uniandes.csw.farmacia.dto.TransaccionClienteDTO;
import co.edu.uniandes.csw.farmacia.entities.TransaccionClienteEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


/**
 *
 * @author ra.ariasr
 */
@Path("transaccionesCliente")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TransaccionClienteResource 
{
    private static final Logger LOGGER = Logger.getLogger(ProveedorResource.class.getName());
    
    @POST
    public TransaccionClienteDTO createTransacionCliente(TransaccionClienteDTO transaccion) throws BusinessLogicException
    {
        return transaccion;
    }
    @GET
    public TransaccionClienteDTO getTransacciones()
    {
        return new TransaccionClienteDTO();
    }
    @GET
    @Path("{transaccionesClienteId: \\d+}")
    public TransaccionClienteDTO getTransaccionCliente(@PathParam("transaccionesClienteId")Long id )
    {
      LOGGER.log(Level.INFO, "TransacionClienteResource getEditorial: input: {0}", id);
        
      TransaccionClienteDTO p = new TransaccionClienteDTO(new TransaccionClienteEntity());
      p.setId(id);
      p.setMonto(200000.0);
      p.setParcial(false);
      p.setTiempo(20.5);
      p.setTipoDePago("Efectivo");
      
      System.out.println("co.edu.uniandes.csw.farmacia.resources.TransaccionCliente.getMonto"+p.getMonto());
      
      return p;
    }
        @DELETE
    @Path("{transaccionesClienteId:\\d+}")
    public void deleteTransaccionCliente(@PathParam("transaccionesClienteId")Long id)
    {
        
    }
    @PUT
    @Path("{transaccionesClienteId:\\d+}")
    public TransaccionClienteDTO refreshDataTransaccionCliente(@PathParam("transaccionesClienteId") Long id, TransaccionClienteDTO proveedor)
    {
        return proveedor;
    }
}
