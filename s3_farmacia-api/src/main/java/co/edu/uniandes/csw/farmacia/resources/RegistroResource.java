/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dto.RegistroDTO;
import co.edu.uniandes.csw.farmacia.ejb.RegistroLogic;
import co.edu.uniandes.csw.farmacia.entities.RegistroEntity;
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
 * @author estudiante
 */
//@Path("registros") En ReviewsResource no hay Path y esta clase es parecida.
@Produces("application/json")
@Consumes("application/json")
public class RegistroResource {
    
    private static final Logger LOGGER = Logger.getLogger(RegistroResource.class.getName());
    
    @Inject
    private RegistroLogic registroLogic;
    
    /**
     * Crea un nuevo registro con la infromacion que recibe en le cuerpo de la peticion
     * y se regresa un objeto identico con un id-generado por la base de datos
     * @param productosId El id del producto el cual se le agrega el registro
     * @param registro El registro que se desea guardar
     * @return JSON - El registo guardado en el atributo id autogenerado
     * @throws BusinessLogicException 
     */
    @POST
    public RegistroDTO createRegistro(@PathParam("productosId") Long productosId, RegistroDTO registro) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "RegistroResource createRegistro: input: {0}", registro.toString());
        RegistroDTO nuevoRegistroDTO = new RegistroDTO(registroLogic.createRegistro( registro.toEntity()));
        LOGGER.log(Level.INFO, "RegistroResource createRegistro: output: {0}", nuevoRegistroDTO.toString());
        return nuevoRegistroDTO;
    }
    
    /**
     * Busca y devuelve todos los registros que existen en un producto
     * @param productosId el Id del producto del cual se buscan los registros
     * @return JSON array - los registros encontrados en el producto
     */
    @GET
    public List<RegistroDTO> getRegistros(@PathParam("productosId") Long productosId) {
        LOGGER.log(Level.INFO, "RegistroResource getRegistros: input: {0}", productosId);
        List<RegistroDTO> listaDTOs = listEntity2DTO(registroLogic.getRegistros(productosId));
        //LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }
    
    /**
     * Busca y devuelve el registro con el ID recibido en la URL, relatica a un
     * prodcuto
     * @param productosId EL ID del producto del cual se buscan los registros
     * @param registrosId El id del registro que se busca
     * @return La reseña encontrada en el prodcuto
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{registrosId: \\d+}")
    public RegistroDTO getRegistro(@PathParam("productosId") Long productosId, @PathParam("registrosId") Long registrosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "RegistroResource getRegistro: input: {0}", registrosId);
        RegistroEntity entity = registroLogic.getRegistro(productosId, registrosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /productos/" + productosId + "/registros/" + registrosId + " no existe.", 404);
        }
        RegistroDTO registroDTO = new RegistroDTO(entity);
        LOGGER.log(Level.INFO, "RegistroResource getRegistro: output: {0}", registroDTO.toString());
        return registroDTO;
    }
    
    /**
     * Actualiza el registro con la informacion que se recibe en el cuerpo de la
     * peticion y se regresa el objeto actualizado
     * @param productosId El ID del producto del cual se guarda el registro
     * @param registrosId El ID del registro que se va a actualizar
     * @param registro El registro que se desea guardar.
     * @return JSON - El registro actualizado
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{registrosId: \\d+}")
    public RegistroDTO updateRegistro(@PathParam("productosId") Long productosId, @PathParam("registrosId") Long registrosId, RegistroDTO registro) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "RegistroResource updateRegistro: input: productosId: {0} , registrosId: {1} , registro:{2}", new Object[]{productosId, registrosId, registro.toString()});
        if (registrosId.equals(registro.getId())) {
            throw new BusinessLogicException("Los ids del Registro no coinciden.");
        }
        RegistroEntity entity = registroLogic.getRegistro(productosId, registrosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /productos/" + productosId + "/registros/" + registrosId + " no existe.", 404);

        }
        RegistroDTO registroDTO = new RegistroDTO(registroLogic.updateRegistro(productosId, registro.toEntity()));
        LOGGER.log(Level.INFO, "RegistroResource updateRegistro: output:{0}", registroDTO.toString());
        return registroDTO;

    }
    
    /**
     * Borra el registro con el id asociado recibido en la URL
     * @param productosId el ID del producto del cual se va a eliminar el registro
     * @param registrosId el ID del registro que se va a eliminar
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{registrosId: \\d+}")
    public void deleteRegistro(@PathParam("productosId") Long productosId, @PathParam("registrosId") Long registrosId) throws BusinessLogicException {
        RegistroEntity entity = registroLogic.getRegistro(productosId, registrosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /productos/" + productosId + "/registros/" + registrosId + " no existe.", 404);
        }
        registroLogic.deleteRegistro(productosId, registrosId);
    }
    
    /**
     * Lista de entidades a DTO
     * Este metodo convierte una lsta de objetos RegistroEntity a una lsita de
     * objetos RegistroDTO
     * @param entityList corresponde a la lista de registros de tipo Entity que
     * vamos a convertir a DTO
     * @return la lista de registros en forma DTO.
     */
    private List<RegistroDTO> listEntity2DTO(List<RegistroEntity> entityList) {
        List<RegistroDTO> list = new ArrayList<RegistroDTO>();
        for (RegistroEntity entity : entityList) {
            list.add(new RegistroDTO(entity));
        }
        return list;
    }
    
    
    
}
