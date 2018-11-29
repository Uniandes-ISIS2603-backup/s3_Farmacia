/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dto.ClienteDTO;
import co.edu.uniandes.csw.farmacia.dto.ClienteDetailDTO;
import co.edu.uniandes.csw.farmacia.ejb.ClienteLogic;
import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
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
 * @author estudiante
 */
@Path("clientes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ClienteResource {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteResource.class.getName());
    
    private static final String C1 = "El recurso /clientes/ ";
    
    private static final String C2  = " no existe";
    
    private static final String C3 = "El recurso /clientes/cedula/ ";
    
    
    @Inject
    ClienteLogic clienteLogic;

    /**
     * Crea un nuevo cliente con la informacion que se recibe en el cuerpo de
     * la peticion y se regresa un objeto identico con un id auto-generado por
     * la base de datos
     * @param cliente {@link ClienteDTO} - El cliente que se desea guardar
     * 
     * @return JSON {@link ClienteDTO} - El cliente guardado con el atrinuto
     * id autogenerado
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de logica que se genera cuando ya existe un cliente.
     */
    @POST
    public ClienteDetailDTO createCliente(ClienteDTO cliente) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "ClienteResource createCliente: input: {0}", cliente);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ClienteEntity clienteEntity = cliente.toEntity();
        // Invoca la lógica para crear el cliente nueva
        ClienteEntity nuevoClienteEntity = clienteLogic.createCliente(clienteEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        ClienteDetailDTO nuevoClienteDTO = new ClienteDetailDTO(nuevoClienteEntity);
        LOGGER.log(Level.INFO, "ClienteResource createCliente: output: {0}", nuevoClienteDTO);
        return nuevoClienteDTO;
    }
    
    /**
     * Busca y devuelve todos los clientes que existen en la aplicacion.
     *
     * @return JSONArray {@link ClienteDTO} - Los clientes encontrados en
     * la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ClienteDetailDTO> getClientes(){
        LOGGER.info("ClienteResource getClientes: input: void");
        List<ClienteDetailDTO> listaClientes = listEntity2DetailDTO(clienteLogic.getClientes());
        LOGGER.log(Level.INFO, "ClienteResource getClientes: output: {0}", listaClientes);
        return listaClientes;
    }
    
    /**
     * Busca el cliente con el id asociado recibido en la URL y la devuelve.
     *
     * @param id Identificador del cliente que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link ClienteDTO} - el cliente buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el cliente.
     */
    @GET
    @Path("{clientesId: \\d+}")
    public ClienteDetailDTO getCliente(@PathParam("clientesId") Long id){
        LOGGER.log(Level.INFO, "ClienteResource getCliente: input: {0}", id);
        ClienteEntity clienteEntity = clienteLogic.getCliente(id);
        if (clienteEntity == null) {
            throw new WebApplicationException(C1 + id + C2, 404);
        }
        ClienteDetailDTO detailDTO = new ClienteDetailDTO(clienteEntity);
        LOGGER.log(Level.INFO, "ClienteResource getCliente: output: {0}", detailDTO);
        return detailDTO;
    }
    
    @GET
    @Path("cedula/{clientesCedula: \\d+}")
    public ClienteDetailDTO getClienteByCedula(@PathParam("clientesCedula") Long cedula){
        LOGGER.log(Level.INFO, "ClienteResource getClienteByCedula: input: {0}", cedula);
        ClienteEntity clienteEntity = clienteLogic.getClienteByCedula(cedula);
        if (clienteEntity == null) {
            throw new WebApplicationException(C3 + cedula + C2, 404);
        }
        ClienteDetailDTO detailDTO = new ClienteDetailDTO(clienteEntity);
        LOGGER.log(Level.INFO, "ClienteResource getClienteByCedula: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Borra el cliente con el id asociado recibido en la URL.
     *
     * @param id Identificador del cliente que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar el cliente.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el cliente.
     */
    @DELETE
    @Path("{clientesId:\\d+}")
    public void deleteCliente(@PathParam("clientesId") Long id)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "ClienteResource deleteCliente: input: {0}", id);
        if (clienteLogic.getCliente(id) == null) {
            throw new WebApplicationException(C1 + id + C2, 404);
        }
        clienteLogic.deleteCliente(id);
        LOGGER.info("ClienteResource deleteCliente: output: void");
    }
    
    /**
     * Borra el cliente con el id asociado recibido en la URL.
     *
     * @param id Identificador del cliente que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar el cliente.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el cliente.
     */
    @DELETE
    @Path("{clientesId:\\d+}/delete")
    public void delete2Cliente(@PathParam("clientesId") Long id)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "ClienteResource deleteCliente: input: {0}", id);
        if (clienteLogic.getCliente(id) == null) {
            throw new WebApplicationException(C1 + id + C2, 404);
        }

                    clienteLogic.delete2Cliente(id);

        LOGGER.info("ClienteResource deleteCliente: output: void");
    }
    
    /**
     * Actualiza el cliente con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param id Identificador del cliente que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param cliente {@link ClienteDTO}El cliente que se desea guardar.
     * @return JSON {@link ClienteDTO} - El cliente guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el cliente a
     * actualizar.
     */
    @PUT
    @Path("{clientesId:\\d+}")
    public ClienteDTO updateCliente ( @PathParam("clientesId") Long id, ClienteDTO cliente)throws WebApplicationException,BusinessLogicException{
         LOGGER.log(Level.INFO, "ClienteResource updateCliente: input: id:{0} , cliente: {1}", new Object[]{id, cliente});
        cliente.setId(id);
        if (clienteLogic.getCliente(id) == null) {
            throw new WebApplicationException(C1 + id + C2, 404);
        }
        ClienteDTO detailDTO = new ClienteDTO(clienteLogic.updateCliente(id, cliente.toEntity()));
        LOGGER.log(Level.INFO, "ClienteResource updateCliente: output: {0}", detailDTO);
        return detailDTO;
    } 
    @Path("{clienteId: \\d+}/transaccionesCliente")
    public Class<TransaccionClienteResource> getTransaccionClienteResource(@PathParam("clienteId") Long clienteId) {
        if (clienteLogic.getCliente(clienteId) == null) {
            throw new WebApplicationException(C1 + clienteId + "/transaccionesCliente no existe.", 404);
        }
        return TransaccionClienteResource.class;
    }
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos ClienteEntity a una lista de
     * objetos ClienteDTO (json)
     *
     * @param entityList corresponde a la lista de clientes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de clientes en forma DTO (json)
     */
    private List<ClienteDetailDTO> listEntity2DetailDTO(List<ClienteEntity> entityList) {
        List<ClienteDetailDTO> list = new ArrayList<>();
        for (ClienteEntity entity : entityList) {
            list.add(new ClienteDetailDTO(entity));
        }
        return list;
    }
    
}
