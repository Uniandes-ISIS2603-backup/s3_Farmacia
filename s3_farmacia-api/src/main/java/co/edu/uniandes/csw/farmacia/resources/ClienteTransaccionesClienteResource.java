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
import co.edu.uniandes.csw.farmacia.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.farmacia.mappers.WebApplicationExceptionMapper;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author estudiante
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteTransaccionesClienteResource {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteTransaccionesClienteResource.class.getName());
    
    //@Inject
    //private ClienteTransaccionesClienteLogic clienteTransaccionesClienteLogic;
    
    @Inject
    private TransaccionClienteLogic transaccionClienteLogic;
    
    /**
     * Guarda una transaccionCliente dentro de un cliente con la informacion que recibe el
     * la URL. Se devuelve la transaccionCliente que se guarda en el cliente.
     *
     * @param clientesId Identificador del cliebte que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param transaccionesClienteId Identificador de la transaccionCliente que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link TransaccionClienteDTO} - La transaccionCliente guardada en el cliente.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la transaccionCliente.
     */
    @POST
    @Path("{transaccionesClienteId: \\d+}")
    public TransaccionClienteDTO addTransaccionCliente(@PathParam("clientesId") Long clientesId, @PathParam("transaccionesClienteId") Long transaccionesClienteId) {
        LOGGER.log(Level.INFO, "ClienteTransaccionesClienteResource addTransaccionCliente: input: clientesId: {0} , transaccionesClienteId: {1}", new Object[]{clientesId, transaccionesClienteId});
       // if (transaccionClienteLogic.getTransaccionCliente(transaccionesClienteId) == null) {
         //   throw new WebApplicationException("El recurso /transaccionesCliente/" + transaccionesClienteId + " no existe.", 404);
        //}
        //TransaccionClienteDTO transaccionClienteDTO = new TransaccionClienteDTO(clienteTransaccionesClienteLogic.addTransaccion(transaccionesClienteId, clientesId));
        //LOGGER.log(Level.INFO, "ClienteTransaccionesClienteResource addTransaccionCliente: output: {0}", transaccionClienteDTO.toString());
        //return transaccionClienteDTO;
        return null;
    }
    
    /**
     * Busca y devuelve todas las transaccionesCliente que existen en el cliente.
     *
     * @param clientesId Identificador dede cliente que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link TransaccionClienteDetailDTO} - Las transaccionesCliente encontrados en la
     * cliente. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<TransaccionClienteDetailDTO> getTransaccionesCliente(@PathParam("clientesId") Long clientesId) {
        LOGGER.log(Level.INFO, "ClienteTransaccionesClienteResource getTransaccionesCliente: input: {0}", clientesId);
        //List<TransaccionClienteDetailDTO> listaDetailDTOs = transaccionesClienteListEntity2DTO(clienteTransaccionesClienteLogic.getTransaccionesCliente(clientesId));
        //LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDetailDTOs.toString());
       // return listaDetailDTOs;
       return null;
    }
    
    /**
     * Busca la transaccion con el id asociado dentro del cliente con id asociado.
     *
     * @param clientesId Identificador del cliente que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param transaccionesClienteId Identificador de la transaccion que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link TransaccionClienteDetailDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la transaccion.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la transaccion del
     * cliente.
     */
    @GET
    @Path("{transaccionesClienteId: \\d+}")
    public TransaccionClienteDetailDTO getTransaccionCliente(@PathParam("transaccionesClienteId") Long transaccionesClienteId, @PathParam("clientesId") Long clientesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ClienteTransaccionesClienteResource getTransaccionCliente: input: clientesID: {0} , transaccionesClienteId: {1}", new Object[]{clientesId, transaccionesClienteId});
        //if (transaccionClienteLogic.getTransaccionCliente(transaccionesClienteId) == null) {
          //  throw new WebApplicationException("El recurso /clientes/" + clientesId + "/transaccionesCliente/" + transaccionesClienteId + " no existe.", 404);
        //}
        //TransaccionClienteDetailDTO transaccionClienteDetailDTO = new TransaccionClientekDetailDTO(clienteTransaccionesClienteLogic.getTransaccionCliente(clientesId, transaccionesClienteId));
       // LOGGER.log(Level.INFO, "ClienteTransaccionesClienteResource getTransaccionCliente: output: {0}", transaccionClienteDetailDTO.toString());
        //return transaccionClienteDetailDTO;
        return null;
    }
    
    /**
     * Remplaza las instancias de transaccionesCliente asociadas a una instancia de Cliente
     *
     * @param clientesId Identificador del cliente que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param transaccionesCliente JSONArray {@link TransaccionClienteDTO} El arreglo de transaccionesCliente nuevo para la
     * cliente.
     * @return JSON {@link TransaccionClienteDTO} - El arreglo de transaccionesCliente guardado en el
     * cliente.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la transaccionCliente.
     */
    @PUT
    public List<TransaccionClienteDetailDTO> replaceTransaccionesCliente(@PathParam("clientesId") Long clientesId, List<TransaccionClienteDetailDTO> transaccionesCliente) {
        LOGGER.log(Level.INFO, "ClienteTransaccionesClienteResource replaceTransaccionesCliente: input: clientesId: {0} , transaccionesCliente: {1}", new Object[]{clientesId, transaccionesCliente.toString()});
        for (TransaccionClienteDetailDTO transaccion : transaccionesCliente) {
           // if (transaccionClienteLogic.getTransaccionCliente(transaccion.getId()) == null) {
                //throw new WebApplicationException("El recurso /transaccionesCliente/" + transaccion.getId() + " no existe.", 404);
            //}
        }
       // List<TransaccionClienteDetailDTO> listaDetailDTOs = transaccionesClienteListEntity2DTO(clienteTransaccionesClienteLogic.replaceTransaccionesCliente(clientesId, transaccionesClienteListDTO2Entity(transaccionesCliente)));
        //LOGGER.log(Level.INFO, "ClienteTransaccionesClienteResource replaceTransaccionesCliente: output: {0}", listaDetailDTOs.toString());
        //return listaDetailDTOs;
        return null;
    }
    
    /**
     * Convierte una lista de TransaccionClienteEntity a una lista de TransaccionClienteDetailDTO.
     *
     * @param entityList Lista de TransaccionClienteEntity a convertir.
     * @return Lista de TransaccionClienteDTO convertida.
     */
    private List<TransaccionClienteDetailDTO> transaccionesClienteListEntity2DTO(List<TransaccionClienteEntity> entityList) {
        List<TransaccionClienteDetailDTO> list = new ArrayList();
        for (TransaccionClienteEntity entity : entityList) {
           // list.add(new TransaccionClienteDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Convierte una lista de TransaccionClienteDetailDTO a una lista de TransaccionClienteEntity.
     *
     * @param dtos Lista de TransaccionClienteDetailDTO a convertir.
     * @return Lista de TransaccionClienteEntity convertida.
     */
    private List<TransaccionClienteEntity> transaccionesClienteListDTO2Entity(List<TransaccionClienteDetailDTO> dtos) {
        List<TransaccionClienteEntity> list = new ArrayList<>();
        for (TransaccionClienteDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
