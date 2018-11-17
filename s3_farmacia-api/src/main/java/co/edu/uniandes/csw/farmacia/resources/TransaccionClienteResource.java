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
@Path("/transaccionesCliente")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TransaccionClienteResource 
{
    @Inject
    private TransaccionClienteLogic logic;
    
    private static final Logger LOGGER = Logger.getLogger(ClienteResource.class.getName());
    
    private static final String TC1 = "El recurso /cliente/ ";
    
    private static final String TC2 = "/transaccionesCliente/";
    
    private static final String TC3 = " no existe.";
    
    
    @POST
    public TransaccionClienteDTO createTransacionCliente(@PathParam("clienteId")Long id,TransaccionClienteDTO transaccion) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "TransaccionResource createTransaccion: input: {0}", transaccion.toString());
        TransaccionClienteDTO DTO = new TransaccionClienteDTO(logic.createTransaccionCliente(id,transaccion.toEntity()));
        LOGGER.log(Level.INFO, "TransaccionResource createTransaccion: output: {0}", DTO.toString());
        return DTO;
    }
    @GET
    public List<TransaccionClienteDetailDTO> getTransacciones(@PathParam("clienteId")Long id)
    {
         LOGGER.log(Level.INFO, "TransaccionClienteResource getTransacciones: input: {0}", id);
        List<TransaccionClienteDetailDTO> lista = listEntity2DetailDTO(logic.getTransaccionesCliente(id));
        LOGGER.log(Level.INFO, "BookResource getBooks: output: {0}", lista.toString());
        return lista;
    }
    
    
    @GET
    @Path("{transaccionesClienteId: \\d+}")
    public TransaccionClienteDetailDTO getTransaccionCliente(@PathParam("clienteId")Long idCli,@PathParam("transaccionesClienteId")Long id  )
    {
        LOGGER.log(Level.INFO, "TransaccionClienteResource getTransaccionCliente: input: {0}", id);
        TransaccionClienteEntity entity = logic.getTransaccionCliente(idCli, id);
        if (entity == null) {
            throw new WebApplicationException(TC1 + idCli + TC2 + id + TC3, 404);
        }
       TransaccionClienteDetailDTO DTO = new TransaccionClienteDetailDTO(entity);
        LOGGER.log(Level.INFO, "TransaccionClienteResource getTransaccion: output: {0}", DTO.toString());
        return DTO;
    }

   
        @DELETE
    @Path("{transaccionesClienteId:\\d+}")
    public void deleteTransaccionCliente(@PathParam("ClienteId")Long idCLi,@PathParam("transaccionClienteId")Long id) throws BusinessLogicException
    {
        TransaccionClienteEntity entity = logic.getTransaccionCliente(idCLi, id);
        if (entity == null) {
            throw new WebApplicationException(TC1 + idCLi + TC2 + id + TC3, 404);
        }
    logic.deleteTransaccionCliente(idCLi, id);
    }
    @PUT
    @Path("{transaccionesClienteId:\\d+}")
    public TransaccionClienteDTO refreshDataTransaccionCliente(@PathParam("clienteId") Long idCli, @PathParam("transaccionClienteId") Long id, TransaccionClienteDTO trans) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "TransaccionClienteResource refreshData: input: clienteId: {0} , transaccionId: {1} , transaccionCliente:{2}", new Object[]{idCli, id, trans.toString()});
        if (id.equals(trans.getId())) {
            throw new BusinessLogicException("Los ids de la transaccion no coinciden.");
        }
        TransaccionClienteEntity entity = logic.getTransaccionCliente(idCli, id);
        if (entity == null) {
            throw new WebApplicationException(TC1 + idCli + TC2 + id + TC3, 404);

        }
        TransaccionClienteDTO reviewDTO = new TransaccionClienteDTO(logic.updateTransaccionCliente(idCli, trans.toEntity()));
        LOGGER.log(Level.INFO, "ReviewResource updateReview: output:{0}", reviewDTO.toString());
        return reviewDTO;
    }
    
       private List<TransaccionClienteDetailDTO> listEntity2DetailDTO(List<TransaccionClienteEntity> entityList) {
        List<TransaccionClienteDetailDTO> list = new ArrayList<>();
        for (TransaccionClienteEntity entity : entityList) {
            list.add(new TransaccionClienteDetailDTO(entity));
        }
        return list;
    }
}