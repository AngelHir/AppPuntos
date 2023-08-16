package com.clases.app

import com.software.componente.app.Message
import com.software.componente.app.ObjectException
import grails.gorm.transactions.Transactional

@Transactional
class CajaService {

    /**
     * Busca y devuelve una instancia de la Caja mediante el id
     * @param id Identificador del usuario
     * @return Instancia del Usuario encontrada
     */
    Caja get(long id) {
        log.info 'Plugin : AppPuntos, Servicio : caja, Metodo : get'
        return Caja.get(id)
    }

    /**
     * Guarda en la base de datos los campos de una Caja, nuevo o actualizado
     * @param cajaInstance Caja a actualizar o crear
     * @return Caja actualizada o creada
     * @throws com.software.componente.app.ObjectException Al encontrar algun error en los campos*/
    Caja save(Caja cajaInstance) throws Exception {
        log.info 'Plugin : AppPuntos, Servicio : caja, Metodo : save'
        try {
            if (cajaInstance.validate() && cajaInstance.save()) {
                log.info 'Plugin : AppPuntos, Servicio : caja, Metodo : save, Completado'
                return cajaInstance
            }
            log.error 'Plugin : AppPuntos,  Servicio : usuario, Metodo : save, Error en Guardado'
            throw new ObjectException(Message.getMensaje(codigo: 'save.fail', parametros: [
                    Message.getMensaje('caja.label', 'Caja')
            ]), cajaInstance)
        }
        catch (org.springframework.dao.OptimisticLockingFailureException e) {
            throw new ObjectException(
                    Message.getMensaje([
                            codigo    : "default.optimistic.locking.failure",
                            parametros: [Message.getMensaje('caja.label', 'Caja')]
                    ]),
                    cajaInstance, true
            )
        }
    }

    /**
     * Creacion de un nueva Caja
     * @param cajaMap Datos necesarios para la creacion de la nueva Caja
     * @return Instancia de la Caja creada
     **/
    Caja create(Map cajaMap) throws Exception {
        log.info 'Plugin : facturacionNomina, Servicio : caja, Metodo : create Iniciando'
        try {
            Caja cajaInstance = new Caja()
            cajaInstance.nombre = cajaMap.nombre
            cajaInstance.sucursal= cajaMap.sucursal as Sucursal

            if (cajaMap.inactivo) {
                cajaInstance.activo = false
            }

            this.save(cajaInstance)
            log.info 'Plugin : facturacionNomina, Servicio : caja, Metodo : create Completado'
            return cajaInstance
        }
        catch (Exception e) {
            log.error 'Plugin : facturacionNomina, Servicio : caja, Metodo : create, Error'
            throw e
        }
    }


    /**
     * Actualizacion de una Caja existente
     * @param cajaMap Datos necesarios para la actualizacion de la Caja
     * @return Instancia de la Caja actualizado
     * @throws RuntimeException Al no encontrar la Caja para actualizar
     **/
    Caja update(Map cajaMap) throws Exception {
        log.info 'Plugin : appPuntos, Servicio : caja, Metodo : update Iniciando'
        try {
            Caja cajaInstance = this.get(cajaMap.id as long)
            if (!cajaInstance) {
                log.error 'Plugin : appPuntos, Servicio : caja, Metodo : update, Error No Encontrado'
                throw new RuntimeException(Message.getMensaje(codigo: 'default.not.found.message', parametros: [
                        Message.getMensaje('caja.label', 'Caja'), cajaInstance.id
                ]))
            }
            cajaInstance.nombre = cajaMap.nombre
            cajaInstance.sucursal= cajaMap.sucursal as Sucursal

            this.save(cajaInstance)
            log.info 'Plugin : appPuntos, Servicio : caja, Metodo : update Completado'
            return cajaInstance
        } catch (Exception e) {
            log.error 'Plugin : appPuntos, Servicio : caja, Metodo : update, Error'
            throw e
        }
    }
}
