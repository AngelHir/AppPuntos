package com.clases.app

import com.software.componente.app.ObjectException
import com.software.componente.app.Message
import grails.gorm.transactions.Transactional

@Transactional
class PersonaService {

    DireccionService direccionService

    /**
     * Busca y devuelve una instancia de la Persona mediante el id
     * @param id Identificador de la Persona
     * @return Instancia de la Persona encontrada
     */
    Persona get(long id) {
        log.info 'Plugin : AppPuntos, Servicio : persona, Metodo : get'
        return Persona.get(id)
    }

    /**
     * Guarda en la base de datos los campos de la Persona nueva o actualizada
     * @param personaInstance Persona a actualizar o crear
     * @return Persona actualizada o creada
     * @throws ObjectException Al encontrar algun error en los campos*/
    Persona save(Persona personaInstance) throws Exception {
        log.info 'Plugin : AppPuntos, Servicio : persona, Metodo : save'
        try {
            if (personaInstance.validate() && personaInstance.save()) {
                log.info 'Plugin : AppPuntos, Servicio : persona, Metodo : save, Completado'
                return personaInstance
            }
            log.error 'Plugin : AppPuntos,  Servicio : persona, Metodo : save, Error en Guardado'
            throw new ObjectException(Message.getMensaje(codigo: 'save.fail', parametros: [
                    Message.getMensaje('persona.label', 'Persona')
            ]), personaInstance)
        }
        catch (org.springframework.dao.OptimisticLockingFailureException e) {
            throw new ObjectException(
                    Message.getMensaje([
                            codigo    : "default.optimistic.locking.failure",
                            parametros: [Message.getMensaje('persona.label', 'Persona')]
                    ]),
                    personaInstance, true
            )
        }
    }

    /**
     * Creacion de una nueva Persona
     * @param personaMap Datos necesarios para la creacion de la nueva Persona
     * @return Instancia de la Persona creada
     **/
    Persona create(Map personaMap) throws Exception {
        log.info 'Plugin : facturacionNomina, Servicio : persona, Metodo : create Iniciando'
        try {
            Persona personaInstance = new Persona()
            personaInstance.direccion = direccionService.create(personaMap)
            personaInstance.nombre = personaMap.nombre
            personaInstance.apellido= personaMap.apellido
            personaInstance.telefono = personaMap.telefono
            personaInstance.email = personaMap.email

            if (personaMap.inactivo) {
                personaInstance.activo = false
            }

            this.save(personaInstance)
            log.info 'Plugin : facturacionNomina, Servicio : persona, Metodo : create Completado'
            return personaInstance
        }
        catch (Exception e) {
            log.error 'Plugin : facturacionNomina, Servicio : persona, Metodo : create, Error'
            throw e
        }
    }


    /**
     * Actualizacion de una Persona existente
     * @param personaMap Datos necesarios para la actualizacion de la Persona
     * @return Instancia de la Persona actualizado
     * @throws RuntimeException Al no encontrar la Persona para actualizar
     **/
    Persona update(Map personaMap) throws Exception {
        log.info 'Plugin : appPuntos, Servicio : persona, Metodo : update Iniciando'
        try {
            Persona personaInstance = this.get(personaMap.id as long)
            if (!personaInstance) {
                log.error 'Plugin : appPuntos, Servicio : persona, Metodo : update, Error No Encontrado'
                throw new RuntimeException(Message.getMensaje(codigo: 'default.not.found.message', parametros: [
                        Message.getMensaje('persona.label', 'Persona'), personaInstance.id
                ]))
            }
            personaInstance.direccion = direccionService.update(personaMap)
            personaInstance.nombre = personaMap.nombre
            personaInstance.apellido= personaMap.apellido
            personaInstance.telefono = personaMap.telefono
            personaInstance.email = personaMap.email

            this.save(personaInstance)
            log.info 'Plugin : appPuntos, Servicio : persona, Metodo : update Completado'
            return personaInstance
        } catch (Exception e) {
            log.error 'Plugin : appPuntos, Servicio : persona, Metodo : update, Error'
            throw e
        }
    }

}
