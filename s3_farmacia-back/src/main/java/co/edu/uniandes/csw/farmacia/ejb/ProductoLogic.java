/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.RegistroEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.ProductoPersistence;
import co.edu.uniandes.csw.farmacia.persistence.RegistroPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jd.pardo
 */
public class ProductoLogic {
    
    public static final int UNIDADES_MIN = Integer.MIN_VALUE;
    
    public static final int UNIDADES_MAX = Integer.MAX_VALUE;
    
    @Inject
    private ProductoPersistence persistence;
    
    @Inject
    private RegistroPersistence registroPersistence;
            
    /**
     * Crea un nuevo registro en la base de datos
     * @param productoEntity producto a agregar en la bd
     * @param idRegistro id del registro asociado a la llegada del producto
     * @return el objeto creado por parametro
     * @throws BusinessLogicException 
     * en caso de que las unidades del producto no sean válidas, o que el registro no exista
     */
    public ProductoEntity create(ProductoEntity productoEntity, Long idRegistro) 
            throws BusinessLogicException {
        RegistroEntity registro = registroPersistence.find(idRegistro);
        if (registro == null || registro.getProducto() != null)
            throw new BusinessLogicException(
                    "El registro no existe o ya tiene un producto asociado");
        if( productoEntity == null || 
                productoEntity.getUnidadesDisponibles() == null || 
                productoEntity.getUnidadesDisponibles() < UNIDADES_MIN ||
                productoEntity.getUnidadesDisponibles() > UNIDADES_MAX ) {
            throw new BusinessLogicException(
                    "El numero de unidades disponibles no es válido"
            );
        }
        List<RegistroEntity> list = productoEntity.getRegistros();
        list.add(registro);
        productoEntity.setRegistros(list);
        registro.setProducto(productoEntity);
        return persistence.create(productoEntity);
    }
    
    /**
     * Asocia el producto con el registro, ambos dados por parámetros
     * @param productoId id del producto
     * @param registroId id del registro
     * @throws BusinessLogicException si no se encuentra el producto o el registro,
     * o si el registro ya se encuentra asociado a un producto
     */
    public void asociate(Long productoId, Long registroId) 
            throws BusinessLogicException {
        ProductoEntity producto = persistence.find(productoId);
        RegistroEntity registro = registroPersistence.find(registroId);
        if (producto == null || registro == null)
            throw new BusinessLogicException("No se encontró el recurso");
        else if (registro.getProducto() != null) {
            throw new BusinessLogicException(
                    "El registro ya tiene un producto asociado");
        }
        List<RegistroEntity> registros = producto.getRegistros();
        registro.setProducto(producto);
        registros.add(registro);
        producto.setRegistros(registros);
        persistence.update(producto);
        registroPersistence.update(registro);
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
