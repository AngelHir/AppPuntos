package com.clases.app

import com.software.componente.app.ObjectException
import com.software.componente.app.Message
import grails.gorm.transactions.Transactional

@Transactional
class DireccionService {


    /**
     * Guarda en la base de datos los campos de la Persona nueva o actualizada
     * @param direccionInstance Persona a actualizar o crear
     * @return Persona actualizada o creada
     * @throws ObjectException Al encontrar algun error en los campos*/
    Direccion save(Direccion direccionInstance) throws Exception {
        log.info 'Plugin : AppPuntos, Servicio : direccion, Metodo : save'
        try {
            if (direccionInstance.validate() && direccionInstance.save()) {
                log.info 'Plugin : AppPuntos, Servicio : direccion, Metodo : save, Completado'
                return direccionInstance
            }
            log.error 'Plugin : AppPuntos,  Servicio : direccion, Metodo : save, Error en Guardado'
            throw new ObjectException(Message.getMensaje(codigo: 'save.fail', parametros: [
                    Message.getMensaje('direccion.label', 'Direccion')
            ]), direccionInstance)
        }
        catch (org.springframework.dao.OptimisticLockingFailureException e) {
            throw new ObjectException(
                    Message.getMensaje([
                            codigo    : "default.optimistic.locking.failure",
                            parametros: [Message.getMensaje('direccion.label', 'Direccion')]
                    ]),
                    direccionInstance, true
            )
        }
    }

    /**
     * Creacion de una nueva Direccion
     * @param direccionMap Datos necesarios para la creacion de la nueva Direccion
     * @return Instancia de la Direccion creada
     **/
    Direccion create(Map direccionMap) throws Exception {
        log.info 'Plugin : facturacionNomina, Servicio : direccion, Metodo : create Iniciando'
        try {
            Direccion direccionInstance = new Direccion()
            direccionInstance.calle = direccionMap.calle
            direccionInstance.numExterior = direccionMap.numExterior
            direccionInstance.numInterior = direccionMap.numInterior
            direccionInstance.estado = direccionMap.estado
            direccionInstance.ciudad = direccionMap.ciudad
            direccionInstance.codigoPostal = direccionMap.codigoPostal



            if (direccionMap.inactivo) {
                direccionInstance.activo = false
            }

            this.save(direccionInstance)
            log.info 'Plugin : facturacionNomina, Servicio : direccion, Metodo : create Completado'
            return direccionInstance
        }
        catch (Exception e) {
            log.error 'Plugin : facturacionNomina, Servicio : direccion, Metodo : create, Error'
            throw e
        }
    }


    /**
     * Actualizacion de una Persona existente
     * @param personaMap Datos necesarios para la actualizacion de la Persona
     * @return Instancia de la Persona actualizado
     * @throws RuntimeException Al no encontrar la Persona para actualizar
     **/
    Direccion update(Map direccionMap) throws Exception {
        log.info 'Plugin : appPuntos, Servicio : direccion, Metodo : update Iniciando'
        try {
            Direccion direccionInstance = this.get(direccionMap.id as long)
            if (!direccionInstance) {
                log.error 'Plugin : appPuntos, Servicio : persona, Metodo : update, Error No Encontrado'
                throw new RuntimeException(Message.getMensaje(codigo: 'default.not.found.message', parametros: [
                        Message.getMensaje('persona.label', 'Persona'), personaInstance.id
                ]))
            }
            direccionInstance.calle = direccionMap.calle
            direccionInstance.numExterior = direccionMap.numExterior
            direccionInstance.numInterior = direccionMap.numInterior
            direccionInstance.estado = direccionMap.estado
            direccionInstance.ciudad = direccionMap.ciudad
            direccionInstance.codigoPostal = direccionMap.codigoPostal

            this.save(personaInstance)
            log.info 'Plugin : appPuntos, Servicio : persona, Metodo : update Completado'
            return personaInstance
        } catch (Exception e) {
            log.error 'Plugin : appPuntos, Servicio : persona, Metodo : update, Error'
            throw e
        }
    }


}
