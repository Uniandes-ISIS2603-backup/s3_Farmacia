/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.ProductoPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jd.pardo
 */
public class ProductoLogic {
    
    public static final int UNIDADES_MIN = 0;
    
    public static final int UNIDADES_MAX = Integer.MAX_VALUE;
    
    @Inject
    private ProductoPersistence persistence;
            
    /**
     * Crea un nuevo registro en la base de datos
     * @param productoEntity producto a agregar en la bd
     * @return el objeto creado por parametro
     * @throws BusinessLogicException 
     * en caso de que las unidades del producto no sean válidas
     */
    public ProductoEntity create(ProductoEntity productoEntity) 
            throws BusinessLogicException {
        if( productoEntity == null || 
                productoEntity.getUnidadesDisponibles() == null || 
                productoEntity.getUnidadesDisponibles() < UNIDADES_MIN ||
                productoEntity.getUnidadesDisponibles() > UNIDADES_MAX ) {
            throw new BusinessLogicException(
                    "El numero de unidades disponibles no es válido"
            );
        }
        return persistence.create(productoEntity);
    }
    
    /**
     * Obtiene los productos creados en la base de datos 
     * @return lista de los productos creados
     */
    public List<ProductoEntity> list() {
        return persistence.list();
    }
    
    /**
     * Obtiene el producto dado por parametro
     * @param id id del producto a buscar
     * @return producto con el id
     * @throws BusinessLogicException si no se encontró el producto
     */
    public ProductoEntity get(Long id) throws BusinessLogicException {
        if(id == null)
            throw new BusinessLogicException("No se encontró el elemento");
        ProductoEntity producto = persistence.find(id);
        if(producto == null)
            throw new BusinessLogicException("No se encontró el elemento");
        else
            return producto;
    }
    
    /**
     * Actualiza el producto con el id ingresado por parametro
     * @param id id del producto a modificar
     * @param productoEntity producto a actualizar
     * @return producto actualizado
     * @throws BusinessLogicException si el nuevo valor de unidades disponibles
     *  no es válido
     */
    public ProductoEntity update(Long id, ProductoEntity productoEntity) throws
            BusinessLogicException{
        productoEntity.setId(id);
        if (id == null)
            throw new BusinessLogicException(
                    "El id no puede ser nulo");
        if ( productoEntity.getUnidadesDisponibles() != null && 
                ( productoEntity.getUnidadesDisponibles() < UNIDADES_MIN ||
                productoEntity.getUnidadesDisponibles() > UNIDADES_MAX )) {
            throw new BusinessLogicException(
                    "La nueva cantidad de unidades disponibles no es válida");
        }
        if (persistence.find(id) == null) {
            throw new BusinessLogicException(
                    "No se encontró el elemento a actualizar");
        }
        return persistence.update(productoEntity);
    }
    
    /**
     * Elimina el producto con el id ingresado por parametro
     * @param id id del elemento a eliminar
     * @throws BusinessLogicException si no existe el producto a eliminar
     */
    public void delete(Long id) throws BusinessLogicException {
        if (id == null || persistence.find(id) == null)
            throw new BusinessLogicException(
                    "No se encontró el elemento a eliminar");
        persistence.delete(id);
    }
}
