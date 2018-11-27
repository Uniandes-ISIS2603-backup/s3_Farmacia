/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dto.ProductoDetailDTO;
import co.edu.uniandes.csw.farmacia.ejb.TransaccionClienteProductosLogic;
import co.edu.uniandes.csw.farmacia.ejb.TransaccionClienteLogic;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author estudiante
 */

@Path("transaccionesCliente/{transaccionesClienteId:\\d+}/productos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TransaccionClienteProductoResource {

    private static final Logger LOGGER = Logger.getLogger(TransaccionClienteProductoResource.class.getName());
    
    private static final String TCP1 = "El recurso /transaccionCliente/";
    
    private static final String TCP2 = " no existe.";


    @Inject
    private TransaccionClienteProductosLogic relacionLogic;

    @Inject
    private TransaccionClienteLogic transLogic;

    @POST
   @Path("{productoId: \\d+}") 
    public void addProducto(@PathParam("transaccionesClienteId") Long transId, @PathParam("productoId")Long productosId, @PathParam("clienteId") Long clienteId) {
        try {
            
            
            LOGGER.log(Level.INFO, "TransaccionClienteProductoResource addProducto: input: transaccionClienteId {0} , ProductoId {1},cleinteId{2}", new Object[]{transId, productosId, clienteId});
            TransaccionClienteEntity trans
                    = transLogic.getTransaccionCliente(clienteId, transId);
            if (trans == null || trans.getProductos() == null) {
                throw new WebApplicationException(TCP1 + transId + TCP2, 404);
            }

            relacionLogic.asociate(clienteId, transId, productosId);

        } catch (BusinessLogicException e) {
            throw new WebApplicationException(e.getMessage() + " 404");
        }
    }

    @GET
    public List<ProductoDetailDTO> getProductos(@PathParam("transaccionesClienteId") Long transId, @PathParam("clienteId") Long idCli) {
        try {
            LOGGER.log(Level.INFO, "TransaccionClienteProductoResource getProductos: input: {0}", transId);
            List<ProductoDetailDTO> lista = productosListEntity2DTO(relacionLogic.getProductos(idCli, transId));
            return lista;
        } catch (BusinessLogicException e) {
            throw new WebApplicationException(e.getMessage() + " 404");
        }
    }

    @GET
    @Path("{productoId: \\d+}")
    public ProductoDetailDTO getProducto(@PathParam("transaccionClienteId") Long transId, @PathParam("productoId") Long productoId, @PathParam("clienteId") Long cliId) {
        try {
            LOGGER.log(Level.INFO, "TransaccionClienteProductoResource getProducto: input: transaccionClienteId {0} , productosId {1} , clienteid{2}", new Object[]{transId, productoId, cliId});
            if (transLogic.getTransaccionCliente(cliId, transId) == null) {
                throw new WebApplicationException(TCP1 + transId + TCP2, 404);
            }
            List<ProductoDetailDTO> detailsDTO = productosListEntity2DTO(relacionLogic.getProductos(cliId, transId));
            boolean encontrado = false;
            ProductoDetailDTO detailDTO = new ProductoDetailDTO();
            for (int i = 0; i < detailsDTO.size() && !encontrado; i++) {
                if (detailsDTO.get(i).getId().equals(productoId)) {
                    detailDTO = detailsDTO.get(i);
                    encontrado = true;
                }
            }
            if (!encontrado) {
                throw new WebApplicationException("El recurso /producto/" + productoId + TCP2, 404);
            }
            LOGGER.log(Level.INFO, "AuthorBooksResource getBook: output: {0}", detailDTO);
            return detailDTO;
        } catch (BusinessLogicException e) {
            throw new WebApplicationException(e.getMessage() + " 404");
        }
    }
    @DELETE
    @Path("{productoId:\\d+}")
    public void removeProducto(@PathParam("transaccionesClienteId") Long transId, @PathParam("productoId") Long productoId, @PathParam("clienteId") Long cliId) {

        try {
            LOGGER.log(Level.INFO, "TransaccionClienteResource removeProducto: input: transaccionClienteId {0} , productosId {1},cliente{2}", new Object[]{transId, productoId, cliId});
            if (transLogic.getTransaccionCliente(cliId, transId) == null) {
                throw new WebApplicationException(TCP1 + transId + TCP2, 404);
            }
            relacionLogic.deasociate(cliId, transId, productoId);
            LOGGER.info("TransaccionClienteResource deleteBook: output: void");
        } catch (BusinessLogicException e) {
            throw new WebApplicationException(e.getMessage() + " 404");
        }
    }


    private List<ProductoDetailDTO> productosListEntity2DTO(List<ProductoEntity> entityList) {
        List<ProductoDetailDTO> list = new ArrayList<>();
        for (ProductoEntity entity : entityList) {
            list.add(new ProductoDetailDTO(entity));
        }
        return list;
    }
}
