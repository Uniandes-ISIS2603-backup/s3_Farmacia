/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;


import co.edu.uniandes.csw.farmacia.dto.TransaccionClienteDTO;
import co.edu.uniandes.csw.farmacia.dto.TransaccionClienteDetailDTO;
import co.edu.uniandes.csw.farmacia.ejb.TransaccionClienteLogic;
import co.edu.uniandes.csw.farmacia.entities.TransaccionClienteEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;


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
    @Inject
    private TransaccionClienteLogic logic;
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorResource.class.getName());
    
    @POST
    public TransaccionClienteDTO createTransacionCliente(TransaccionClienteDTO transaccion) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "TransaccionResource createTransaccion: input: {0}", transaccion.toString());
        TransaccionClienteDTO DTO = new TransaccionClienteDTO(logic.createTransaccionCliente(transaccion.toEntity()));
        LOGGER.log(Level.INFO, "TransaccionResource createTransaccion: output: {0}", DTO.toString());
        return DTO;
    }
    @GET
    public List<TransaccionClienteDetailDTO> getTransacciones()
    {
         LOGGER.info("BookResource getBooks: input: void");
        List<TransaccionClienteDetailDTO> listaBooks = listEntity2DetailDTO(logic.getTransaccionesCliente());
        LOGGER.log(Level.INFO, "BookResource getBooks: output: {0}", listaBooks.toString());
        return listaBooks;
    }
    
    /*
    @GET
    @Path("{transaccionesClienteId: \\d+}")
    public TransaccionClienteDetailDTO getTransaccionCliente(@PathParam("transaccionesClienteId")Long id )
    {
       LOGGER.log(Level.INFO, "BookResource getBook: input: {0}", id);
        TransaccionClienteEntity transaccionEntity = logic.getTransaccionCliente(id);
        if (transaccionEntity == null) {
            throw new WebApplicationException("El recurso /books/" + id + " no existe.", 404);
        }
        TransaccionClienteDetailDTO DetailDTO = new TransaccionClienteDetailDTO(transaccionEntity);
        LOGGER.log(Level.INFO, "BookResource getBook: output: {0}", bookDetailDTO.toString());
        return DetailDTO;
    }
*/
   
        @DELETE
    @Path("{transaccionesClienteId:\\d+}")
    public void deleteTransaccionCliente(@PathParam("transaccionesClienteId")Long id) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "TransaccionClienteResource deleteBook: input: {0}", id);
        TransaccionClienteEntity entity = logic.getTransaccionCliente(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /transaccionCliente/" + id + " no existe.", 404);
        }
        
        logic.deleteTransaccionCliente( id );
        LOGGER.info("TransaccionClienteResource deleteBook: output: void");
    }
    @PUT
    @Path("{transaccionesClienteId:\\d+}")
    public TransaccionClienteDTO refreshDataTransaccionCliente(@PathParam("transaccionesClienteId") Long id, TransaccionClienteDTO proveedor)
    {
        return proveedor;
    }
    
       private List<TransaccionClienteDetailDTO> listEntity2DetailDTO(List<TransaccionClienteEntity> entityList) {
        List<TransaccionClienteDetailDTO> list = new ArrayList<>();
        for (TransaccionClienteEntity entity : entityList) {
            list.add(new TransaccionClienteDetailDTO(entity));
        }
        return list;
    }
}