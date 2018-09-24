
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dto.ProductoDetailDTO;
import co.edu.uniandes.csw.farmacia.ejb.ProveedorLogic;
import co.edu.uniandes.csw.farmacia.ejb.ProveedorProductosLogic;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author fj.gonzalez
 */
@Path("proveedores/{id:\\d+}/productos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ProveedorProductosResource
{
    
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorProductosResource.class.getName());
    
    @Inject
     private ProveedorProductosLogic proveedorProductosLogic;
    
    @Inject
    private ProveedorLogic proveedorLogic;
    
           
    @POST
    @Path("{productosId: \\d+}")
    public void addProducto(@PathParam("id") Long provId, @PathParam("productosId") Long productosId) {
        LOGGER.log(Level.INFO, "ProveedorProductosResource addProducto: input: provId {0} , productosId {1}", new Object[]{provId, productosId});
        if (proveedorLogic.getProveedor(provId) == null) {
            throw new WebApplicationException("El recurso /proveedor/" + provId + " no existe.", 404);
        }
        proveedorProductosLogic.addProducto(provId, productosId);
    }        
@GET
public List<ProductoDetailDTO> getProductos(@PathParam("id") Long provId ) 
{
    LOGGER.log(Level.INFO, "ProveedorProductosResource getProductos: input: {0}", provId);
    List<ProductoDetailDTO> lista = productosListEntity2DTO(proveedorProductosLogic.getProductos(provId));
    return lista;
}

    @GET
    @Path("{productosId: \\d+}")
    public ProductoDetailDTO getProducto(@PathParam("id") Long provId, @PathParam("productosId") Long productosId ) {
        LOGGER.log(Level.INFO, "ProveedorProductosResource getProducto: input: transaccionClienteId {0} , productosId {1}", new Object[]{provId, productosId});
        if (proveedorLogic.getProveedor(provId) == null) {
            throw new WebApplicationException("El recurso /proveedor/" + provId + " no existe.", 404);
        }
        List<ProductoDetailDTO> detailsDTO = productosListEntity2DTO(proveedorProductosLogic.getProductos(provId));
        boolean encontrado=false;
        ProductoDetailDTO detailDTO= new ProductoDetailDTO();
        for(int i =0; i<detailsDTO.size() && !encontrado;i++)
        {
            if(detailsDTO.get(i).getId().equals(productosId))
            {
                detailDTO=detailsDTO.get(i);
                encontrado=true;
            }
        }
        if(!encontrado)
        {
            throw new WebApplicationException("El recurso /producto/" + productosId + " no existe.", 404);
        }
        LOGGER.log(Level.INFO, "AuthorBooksResource getBook: output: {0}", detailDTO);
        return detailDTO;
    }

    @DELETE
    @Path("{productosId: \\d+}")
    public void removeProducto(@PathParam("id") Long provId, @PathParam("productosId") Long productoId) {
        
        LOGGER.log(Level.INFO, "ProveedorProductosResoruce removeProducto: input: id {0} , productosId {1}", new Object[]{provId,productoId});
        if (proveedorLogic.getProveedor(productoId) == null) {
            throw new WebApplicationException("El recurso /transaccion/" + provId + " no existe.", 404);
        }
        proveedorProductosLogic.removeProducto( provId, productoId);
        LOGGER.info("ProveedorProductosResoruce removeProducto: output: void");
    }

    private List<ProductoEntity> productosListDTO2Entity(List<ProductoDetailDTO> dtos) {
        List<ProductoEntity> list = new ArrayList<>();
        for (ProductoDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
   private List<ProductoDetailDTO> productosListEntity2DTO(List<ProductoEntity> entityList) {
        List<ProductoDetailDTO> list = new ArrayList<>();
        for (ProductoEntity entity : entityList) {
            list.add(new ProductoDetailDTO(entity));
        }
        return list;
    }
}