/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dto.TransaccionProveedorDetailDTO;
import co.edu.uniandes.csw.farmacia.ejb.ProveedorLogic;
import co.edu.uniandes.csw.farmacia.ejb.TransaccionProveedorLogic;
import co.edu.uniandes.csw.farmacia.entities.TransaccionProveedorEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author estudiante
 */
public class ProveedorTransaccionesProveedorResource {

    private static final Logger LOGGER = Logger.getLogger(ProveedorTransaccionesProveedorResource.class.getName());

  //  @Inject
   // private ProveedorTransaccionesProveedorLogic provTransLogic;

    @Inject
    private TransaccionProveedorLogic transcProLogic;

    @POST
    @Path("{transaccionId: \\d+}")
    public TransaccionProveedorDetailDTO addTransaccionProveedor(@PathParam("id") Long provId, @PathParam("transaccionId") Long transaccionId) {
        LOGGER.log(Level.INFO, "ProveedorTransaccionesProveedorResource addTransaccionProveedor: input: id {0} , transaccionId {1}", new Object[]{provId, transaccionId});
        if (transcProLogic.getTransaccionProveedor(provId, transaccionId) != null) {
            //TransaccionProveedorDetailDTO transDetailDTO = new TransaccionProveedorDetailDTO(provLogic.addTransaccionProveedor());
            //  LOGGER.log(Level.INFO, "ProveedorTransaccionesProveedorResource addTransaccionProveedor: output: {0}", transDetailDTO.toString());
            // return transDetailDTO;
        } else {
            throw new WebApplicationException("El recurso /transaccionProveedor/" + transaccionId + " no existe.", 404);
        }
        return null;
    }

    @GET
    public List<TransaccionProveedorDetailDTO> getTransaccionesProveedor(@PathParam("id") Long id) {
        LOGGER.log(Level.INFO, "ProveedorTransaccionesProveedorResource getTransaccionesProveedor: input{0} ", id);
       // List<TransaccionProveedorDetailDTO> listaa =  transaccionProveedorListEntity2DTO(provTransaccLogic.getTransaccionesProveedor(id));
       // LOGGER.log(Level.INFO, "ProveedorTransaccionesProveedor getTransaccionesProveedor: output{0} ", listaa.toString());
       // return listaa;
       return null;
    }
    @GET
    @Path("{transaccionId: \\d+}")
    public TransaccionProveedorDetailDTO getTransaccionProveedor(@PathParam("id") Long provId ,@PathParam("transaccionId") Long transaccionId )
    {
        LOGGER.log(Level.INFO, "ProveedorTransaccionesProveedor getProveedor: input: provId {0} , transaccionId {1}", new Object[]{provId, transaccionId});
        if(transcProLogic.getTransaccionProveedor(provId,transaccionId)== null)
        {
            throw new WebApplicationException("El recurso /transaccionProveedor/" + transaccionId + " no existe.", 404);
        }
       // TransaccionProveedorDetailDTO transDetail = new TransaccionProveedorDetailDTO(provTransLogic.getTransaccionProveedor(provId, transaccionId));
       // LOGGER.log(Level.INFO, "ProveedorTransaccionesProveedorResource getTransaccionProveedor: output: {0}", transDetail.toString());
       // return transDetail;
       return null;
    }
    @PUT
    public List<TransaccionProveedorDetailDTO> replaceTransaccionesProveedor(@PathParam("id") Long provId, List<TransaccionProveedorDetailDTO> transaccionesProveedor)
    {
        LOGGER.log(Level.INFO, "ProveedorTransaccionesProveedor replaceTransaccionesProveedor: input: provId {0} , transaccionesProveedor {1}", new Object[]{provId, transaccionesProveedor.toString()});
        for (TransaccionProveedorDetailDTO transProv : transaccionesProveedor) {
           // if (transcProLogic.getAuthor(transProv.getId()) == null) {
             //   throw new WebApplicationException("El recurso /transaccionProveedor/" + transProv.getId() + " no existe.", 404);
            //}
        }
       // List<TransaccionProveedorDetailDTO> lista = transaccionProveedorListEntity2DTO(provTransLogic.replaceAuthors(provId, transaccionProveedorListDTO2Entity(transaccionesProveedor)));
      //  LOGGER.log(Level.INFO, "ProveedorTransaccionesProveedor replaceTransaccionesProveedor: output:{0}", lista.toString());
      //  return lista;
        return null;
    }
    
    @DELETE
    @Path("{authorsId: \\d+}")
    public void removeTransaccionProveedor(@PathParam("id") Long provId, @PathParam("transaccionId") Long transaccionId) {
        LOGGER.log(Level.INFO, "ProveedorTransaccionesProveedor removeTransaccionProveedor: input: provId {0} , transaccionId {1}", new Object[]{provId, transaccionId});
        if (transcProLogic.getTransaccionProveedor(provId,transaccionId) == null) {
            throw new WebApplicationException("El recurso /transaccionProveedor/" + transaccionId + " no existe.", 404);
        }
      //  provTransLogic.removeAuthor(provId, transaccionId);
        LOGGER.info("ProveedorTransaccionesProveedor removeTransaccionProveedor: output: void");
    }
    

    private List<TransaccionProveedorDetailDTO> transaccionProveedorListEntity2DTO(List<TransaccionProveedorEntity> entityList) {
        List<TransaccionProveedorDetailDTO> list = new ArrayList<>();
        for (TransaccionProveedorEntity entity : entityList) {
            // list.add(new TransaccionProveedorDetailDTO(entity));
        }
        return list;
    }
    
    private List<TransaccionProveedorEntity> transaccionProveedorListDTO2Entity(List<TransaccionProveedorDetailDTO> dtos) {
        List<TransaccionProveedorEntity> list = new ArrayList<>();
        for (TransaccionProveedorDetailDTO dto : dtos) {
          //  list.add(dto.toEntity());
        }
        return list;
    }

}
