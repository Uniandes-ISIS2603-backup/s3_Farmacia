/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.RegistroEntity;
import co.edu.uniandes.csw.farmacia.persistence.RegistroPersistence;
import co.edu.uniandes.csw.farmacia.persistence.ProductoPersistence;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 *
 * @author estudiante
 */
@Stateless
public class RegistroLogic {
    
    private static final Logger LOGGER = Logger.getLogger(RegistroLogic.class.getName());

    @Inject
    private RegistroPersistence persistence;

    @Inject
    private RegistroPersistence registroPersistence;
    
    /**
     * Guardar un nuevo registrp
     *
     * @param registroEntity La entidad de tipo libro del nuevo registro a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el ISBN es inválido o ya existe en la
     * persistencia.
     
     
    public RegistroEntity createRegistro(RegistroEntity registroEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del registro");
        if (bookEntity.getEditorial() == null || editorialPersistence.find(bookEntity.getEditorial().getId()) == null) {
            throw new BusinessLogicException("La editorial es inválida");
        }
        if (!validateISBN(bookEntity.getIsbn())) {
            throw new BusinessLogicException("El ISBN es inválido");
        }
        if (persistence.findByISBN(bookEntity.getIsbn()) != null) {
            throw new BusinessLogicException("El ISBN ya existe");
        }
        persistence.create(bookEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del libro");
        return bookEntity;
    }
    */
     
    
    /**
     * Devuelve todos los registros que hay en la base de datos.
     *
     * @return Lista de entidades de tipo registro.
     */
    public List<RegistroEntity> getRegistros() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los registros");
        List<RegistroEntity> registros = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los registros");
        return registros;
    }
    
    /**
     * Busca un registro por ID
     *
     * @param registroId El id del registro a buscar
     * @return El registro encontrado, null si no lo encuentra.
     */
    public RegistroEntity getRegistro(Long registroId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el registro con id = {0}", registroId);
        RegistroEntity registroEntity = persistence.find(registroId);
        if (registroEntity == null) {
            LOGGER.log(Level.SEVERE, "El libro con el id = {0} no existe", registroId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el registro con id = {0}", registroId);
        return registroEntity;
    }
    
    /**
     * Actualizar un registro por ID
     *
     * @param registroId El ID del registro a actualizar
     * @param registroEntity La entidad del registro con los cambios deseados
     * @return La entidad del registro luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public RegistroEntity updateRegistro(Long registroId, RegistroEntity registroEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el registro con id = {0}", registroId);
        //if (!validateISBN(bookEntity.getIsbn())) {
          //  throw new BusinessLogicException("El ISBN es inválido");
        //}
        RegistroEntity newEntity = persistence.update(registroEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el registro con id = {0}", registroEntity.getId());
        return newEntity;
    }
    
    /**
     * Eliminar un registro por ID
     *
     * @param registroId El ID del registro a eliminar
     */
    public void deleteRegistro(Long registroId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el registro con id = {0}", registroId);
        persistence.delete(registroId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el registro con id = {0}", registroId);
    }
    
    /**
     * Verifica que el ISBN no sea invalido.
     *
     * @param isbn a verificar
     * @return true si el ISBN es valido.
     
    private boolean validateISBN(String isbn) {
        return !(isbn == null || isbn.isEmpty());
    }
    */

    
}
