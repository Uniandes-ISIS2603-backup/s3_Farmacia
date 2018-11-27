/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dto.TransaccionProveedorDTO;
import co.edu.uniandes.csw.farmacia.dto.TransaccionProveedorDetailDTO;
import co.edu.uniandes.csw.farmacia.ejb.TransaccionProveedorLogic;
import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author jd.florezg1
 */
 @Path("/transaccionProveedor")
 @Produces("application/json")
 @Consumes("application/json")
public class TransaccionProveedorResource extends TransaccionProveedorDTO {
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorResource.class.getName());
    
    @Inject
    private TransaccionProveedorLogic transaccionProveedorLogic;
     /**
     * @param proveedorId
     * @param id
      * @return Informacion de la transaccion.
      */
    @GET
    @Path("{transaccionProveedorId: \\d+}" )
    public TransaccionProveedorDetailDTO getTransaccionProveedor(@PathParam("id") Long proveedorId, @PathParam("transaccionProveedorId")Long id)
    {
      LOGGER.log(Level.INFO, "TransaccionProveedorResource getTransaccionProveedor: input: {0}", id);
      TransaccionProveedorEntity entity = transaccionProveedorLogic.getTransaccionProveedor(proveedorId, id);
      if (entity == null) {
            throw new WebApplicationException("El recurso /proveedor/" + proveedorId + "/transaccionProveedor/" + id + " no existe.", 404);
        }
      TransaccionProveedorDetailDTO transaccionProveedorDTO = new TransaccionProveedorDetailDTO(entity);
      LOGGER.log(Level.INFO, "transaccionProveedorResource getransaccionProveedor: output: {0}", transaccionProveedorDTO);
        return transaccionProveedorDTO;    
    }
    
    /**
     * Busca y devuelve todas las transacciónes que existen en un proveedor.
     *
     * @param proveedorId El ID del proveedor del cual se buscan las transacciónes
     * @return JSONArray {@link ReviewDTO} - Las transacciónes encontradas en el
     * proveedor. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<TransaccionProveedorDTO> getTransaccionesProveedor(@PathParam("id") Long proveedorId) {
        LOGGER.log(Level.INFO, "TransaccionProveedorResource getTransaccionProveedor: input: {0}", proveedorId);
        List<TransaccionProveedorDTO> listaDTOs = listEntity2DTO(transaccionProveedorLogic.getTransaccionesProveedor(proveedorId));
        return listaDTOs;
    }
    
    /**
     * Cambia los datos de la transacción por los que llegan por parametro.
     * @param proveedorId
     * @param transaccionProveedorId
     * @param transaccionProveedor
     * @return 
     * @throws co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException 
     */
    @PUT
    @Path("{transaccionProveedorId: \\d+}" )
    public TransaccionProveedorDTO updateInformacion(@PathParam("id") Long proveedorId, @PathParam("transaccionProveedorId") Long transaccionProveedorId, TransaccionProveedorDTO transaccionProveedor) throws BusinessLogicException, BusinessLogicException
    {
        LOGGER.log(Level.INFO, "TransaccionProveedorResource updateTransaccionProveedor: input: proveedorId: {0} , transaccionProveedorId: {1} , transaccionProveedor:{2}", new Object[]{proveedorId, transaccionProveedorId, transaccionProveedor});

        TransaccionProveedorEntity entity = transaccionProveedorLogic.getTransaccionProveedor(proveedorId, transaccionProveedorId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /proveedor/" + proveedorId + "/transaccionProveedor/" + transaccionProveedorId + " no existe.", 404);
            
        }
        transaccionProveedor.setId(entity.getId());
        
        TransaccionProveedorDTO transaccionProveedorDTO = new TransaccionProveedorDTO(transaccionProveedorLogic.updateTransaccionProveedor(proveedorId, transaccionProveedor.toEntity()));
        LOGGER.log(Level.INFO, "TransaccionProveedorResource updateTransaccionProveedor: output:{0}", transaccionProveedorDTO);
        return transaccionProveedorDTO;
    }
    
    @POST
    public TransaccionProveedorDetailDTO createTransaccionProveedor(@PathParam("id") Long proveedorId, TransaccionProveedorDTO transaccionProveedor) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "TransaccionProveedorResource createTransaccionProveedor: input: {0}", transaccionProveedor);
        TransaccionProveedorDetailDTO nuevaTransaccionProveedorDTO = new TransaccionProveedorDetailDTO(transaccionProveedorLogic.createTransaccionProveedor(proveedorId, transaccionProveedor.toEntity() ));
        LOGGER.log(Level.INFO, "TransaccionProveedorResource createTransaccionPRoveedor: output: {0}", nuevaTransaccionProveedorDTO);
        return nuevaTransaccionProveedorDTO;
    }
    
    /**
     * Borra la TransaccionProveedor con el id asociado recibido en la URL.
     *
     * @param proveedorId El ID del proveedor del cual se va a eliminar la reseña.
     * @param transaccionProveedorId El ID de la reseña que se va a eliminar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la transaccionProveedor.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la transaccionProveedor.
     */
    @DELETE
    @Path("{transaccionProveedorId: \\d+}")
    public void deleteTransaccionProveedor(@PathParam("id") Long proveedorId, @PathParam("transaccionProveedorId") Long transaccionProveedorId) throws BusinessLogicException {
        TransaccionProveedorEntity entity = transaccionProveedorLogic.getTransaccionProveedor(proveedorId, transaccionProveedorId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /proveedor/" + proveedorId + "/transaccionProveedor/" + transaccionProveedorId + " no existe.", 404);
        }
        transaccionProveedorLogic.deleteTransaccionProveedor(proveedorId, transaccionProveedorId);
    }
    
    /**
     * Lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PrizeEntity a una lista de
     * objetos TransaccionProveedorDTO (json)
     *
     * @param entityList corresponde a la lista de reseñas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de transacciones en forma DTO (json)
     */
    private List<TransaccionProveedorDTO> listEntity2DTO(List<TransaccionProveedorEntity> entityList) {
        List<TransaccionProveedorDTO> list = new ArrayList<>();
        for (TransaccionProveedorEntity entity : entityList) {
            list.add(new TransaccionProveedorDTO(entity));
        }
        return list;
    }
    
       @Path("{transaccionProveedorId:\\d+}/productos")
     public Class<TransaccionProveedorProductoResource> getProductos()
      {
          return TransaccionProveedorProductoResource.class;
      }
    
}
