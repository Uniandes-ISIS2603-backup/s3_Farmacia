/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dto.ProductoDetailDTO;
import co.edu.uniandes.csw.farmacia.ejb.TransaccionProveedorLogic;
import co.edu.uniandes.csw.farmacia.ejb.TransaccionProveedorProductoLogic;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author jd.florezg1
 */
@Path("transaccionProveedor/{transaccionProveedorId:\\d+}/productos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TransaccionProveedorProductoResource {

    @Inject
    private TransaccionProveedorProductoLogic transaccionProveedorProductoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private TransaccionProveedorLogic transaccionProveedorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(TransaccionProveedorProductoResource.class.getName());

    @GET
    public List<ProductoDetailDTO> getProductos(@PathParam("id") Long proveedorId, @PathParam("transaccionProveedorId") Long transaccionProveedorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TransaccionProveedorProductosResource getProductos: input: {0}", transaccionProveedorId);
        List<ProductoDetailDTO> listaDetailDTOs = productosListEntity2DTO(transaccionProveedorProductoLogic.getProductos(proveedorId, transaccionProveedorId));
        LOGGER.log(Level.INFO, "TransaccionProveedorProductosResource getProductos: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    @POST
    @Path("{productoId:\\d+}")
    public void asociarProducto(@PathParam("id") Long proveedorId, @PathParam("transaccionProveedorId") Long transaccionProveedorId, @PathParam("productoId") Long productoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TransaccionProveedorProductosResource asociarProducto: input: proveedorId: {0} , transaccionProveedorId: {1}, productoId: {2}", new Object[]{proveedorId, transaccionProveedorId, productoId});
        if (transaccionProveedorLogic.getTransaccionProveedor(proveedorId, transaccionProveedorId) == null) {
            throw new WebApplicationException("El recurso /transaccionProveedor/" + transaccionProveedorId + " no existe.", 404);
        }
        transaccionProveedorProductoLogic.asociate(proveedorId, transaccionProveedorId, productoId);
    }

    @DELETE
    @Path("{productoId:\\d+}")
    public void desasociarProducto(@PathParam("id") Long proveedorId, @PathParam("transaccionProveedorId") Long transaccionProveedorid, @PathParam("productoId") Long productoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TransaccionProveedorProductosResource desasociarProducto: input: proveedorId: {0} transaccionProveedorId{1} productoId {2}", new Object[]{proveedorId, transaccionProveedorid, productoId});
        if (transaccionProveedorLogic.getTransaccionProveedor(proveedorId, transaccionProveedorid) == null) {
            throw new WebApplicationException("El recurso /transaccionProveedor/" + transaccionProveedorid + " no existe.", 404);
        }
        transaccionProveedorProductoLogic.deasociate(proveedorId, transaccionProveedorid, productoId);
    }

    private List<ProductoDetailDTO> productosListEntity2DTO(List<ProductoEntity> entityList) {
        List<ProductoDetailDTO> list = new ArrayList();
        for (ProductoEntity entity : entityList) {
            list.add(new ProductoDetailDTO(entity));
        }
        return list;
    }

}
