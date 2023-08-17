package com.clases.app

import com.software.componente.app.ObjectException
import com.software.componente.app.Message
import grails.gorm.transactions.Transactional

@Transactional
class SesionService {

    /**
     * Busca y devuelve una instancia de la Sesion mediante el id
     * @param id Identificador de la Sesion
     * @return Instancia del Usuario encontrada
     */
    Sesion get(long id) {
        log.info 'Plugin : AppPuntos, Servicio : sesion, Metodo : get'
        return Sesion.get(id)
    }

    /**
     * Guarda en la base de datos los campos de una Sesion, nuevo o actualizado
     * @param sesionInstance Sesion a actualizar o crear
     * @return Sesion actualizada o creada
     * @throws com.software.componente.app.ObjectException Al encontrar algun error en los campos*/
    Sesion save(Sesion sesionInstance) throws Exception {
        log.info 'Plugin : AppPuntos, Servicio : sesion, Metodo : save'
        try {
            if (sesionInstance.validate() && sesionInstance.save()) {
                log.info 'Plugin : AppPuntos, Servicio : sesion, Metodo : save, Completado'
                return sesionInstance
            }
            log.error 'Plugin : AppPuntos,  Servicio : sesion, Metodo : save, Error en Guardado'
            throw new ObjectException(Message.getMensaje(codigo: 'save.fail', parametros: [
                    Message.getMensaje('sesion.label', 'Sesion')
            ]), sesionInstance)
        }
        catch (org.springframework.dao.OptimisticLockingFailureException e) {
            throw new ObjectException(
                    Message.getMensaje([
                            codigo    : "default.optimistic.locking.failure",
                            parametros: [Message.getMensaje('sesion.label', 'Sesion')]
                    ]),
                    sesionInstance, true
            )
        }
    }

    /**
     * Creacion de un nueva Sesion
     * @param sesionMap Datos necesarios para la creacion de la nueva Sesion
     * @return Instancia de la Sesion creada
     **/
    Sesion create(Map sesionMap) throws Exception {
        log.info 'Plugin : facturacionNomina, Servicio : sesion, Metodo : create Iniciando'
        try {
            Sesion sesionInstance = new Sesion()
            sesionInstance.empleado = sesionMap.empleado as Empleado
            sesionInstance.saldoInicial= sesionMap.saldoInicial as BigDecimal

            if (sesionMap.inactivo) {
                sesionInstance.activo = false
            }

            this.save(sesionInstance)
            log.info 'Plugin : facturacionNomina, Servicio : sesion, Metodo : create Completado'
            return sesionInstance
        }
        catch (Exception e) {
            log.error 'Plugin : facturacionNomina, Servicio : sesion, Metodo : create, Error'
            throw e
        }
    }


    /**
     * Actualizacion de una Sesion existente
     * @param sesionMap Datos necesarios para la actualizacion de la Sesion
     * @return Instancia de la Sesion actualizado
     * @throws RuntimeException Al no encontrar la Sesion para actualizar
     **/
    Sesion update(Map sesionMap) throws Exception {
        log.info 'Plugin : appPuntos, Servicio : sesion, Metodo : update Iniciando'
        try {
            Sesion sesionInstance = this.get(sesionMap.id as long)
            if (!sesionInstance) {
                log.error 'Plugin : appPuntos, Servicio : sesion, Metodo : update, Error No Encontrado'
                throw new RuntimeException(Message.getMensaje(codigo: 'default.not.found.message', parametros: [
                        Message.getMensaje('sesion.label', 'Sesion'), sesionInstance.id
                ]))
            }
            sesionInstance.empleado = sesionMap.empleado as Empleado
            sesionInstance.saldoInicial= sesionMap.saldoInicial as BigDecimal

            this.save(sesionInstance)
            log.info 'Plugin : appPuntos, Servicio : sesion, Metodo : update Completado'
            return sesionInstance
        } catch (Exception e) {
            log.error 'Plugin : appPuntos, Servicio : sesion, Metodo : update, Error'
            throw e
        }
    }


}