package com.clases.app

import com.software.componente.app.ObjectException
import com.software.componente.app.Message
import grails.gorm.transactions.Transactional

@Transactional
class SucursalService {

    DireccionService direccionService

    /**
     * Busca y devuelve una instancia de la Sucursal mediante el id
     * @param id Identificador de la Sucursal
     * @return Instancia de la Sucursal encontrada
     */
    Sucursal get(long id) {
        log.info 'Plugin : AppPuntos, Servicio : sucursal, Metodo : get'
        return Sucursal.get(id)
    }

    /**
     * Guarda en la base de datos los campos de una Sucursal, nuevo o actualizado
     * @param sucursalInstance Sucursal a actualizar o crear
     * @return Sucursal actualizada o creada
     * @throws com.software.componente.app.ObjectException Al encontrar algun error en los campos*/
    Sucursal save(Sucursal sucursalInstance) throws Exception {
        log.info 'Plugin : AppPuntos, Servicio : sucursal, Metodo : save'
        try {
            if (sucursalInstance.validate() && sucursalInstance.save()) {
                log.info 'Plugin : AppPuntos, Servicio : sucursal, Metodo : save, Completado'
                return sucursalInstance
            }
            log.error 'Plugin : AppPuntos,  Servicio : sucursal, Metodo : save, Error en Guardado'
            throw new ObjectException(Message.getMensaje(codigo: 'save.fail', parametros: [
                    Message.getMensaje('sucursal.label', 'Sucursal')
            ]), sucursalInstance)
        }
        catch (org.springframework.dao.OptimisticLockingFailureException e) {
            throw new ObjectException(
                    Message.getMensaje([
                            codigo    : "default.optimistic.locking.failure",
                            parametros: [Message.getMensaje('sucursal.label', 'Sucursal')]
                    ]),
                    sucursalInstance, true
            )
        }
    }

    /**
     * Creacion de un nueva Sucursal
     * @param sucursalMap Datos necesarios para la creacion de la nueva Sucursal
     * @return Instancia de la Sucursal creada
     **/
    Sucursal create(Map sucursalMap) throws Exception {
        log.info 'Plugin : facturacionNomina, Servicio : sucursal, Metodo : create Iniciando'
        try {
            Sucursal sucursalInstance = new Sucursal()
            sucursalInstance.nombre = sucursalMap.nombre
            sucursalInstance.clave = sucursalMap.clave
            sucursalInstance.direccion= direccionService.create(sucursalMap)

            if (sucursalMap.inactivo) {
                sucursalInstance.activo = false
            }

            this.save(sucursalInstance)
            log.info 'Plugin : facturacionNomina, Servicio : sucursal, Metodo : create Completado'
            return sucursalInstance
        }
        catch (Exception e) {
            log.error 'Plugin : facturacionNomina, Servicio : sucursal, Metodo : create, Error'
            throw e
        }
    }


    /**
     * Actualizacion de una Sucursal existente
     * @param sucursalMap Datos necesarios para la actualizacion de la Sucursal
     * @return Instancia de la Sucursal actualizada
     * @throws RuntimeException Al no encontrar la Sucursal para actualizar
     **/
    Sucursal update(Map sucursalMap) throws Exception {
        log.info 'Plugin : appPuntos, Servicio : sucursal, Metodo : update Iniciando'
        try {
            Sucursal sucursalInstance = this.get(sucursalMap.id as long)
            if (!sucursalInstance) {
                log.error 'Plugin : appPuntos, Servicio : sucursal, Metodo : update, Error No Encontrado'
                throw new RuntimeException(Message.getMensaje(codigo: 'default.not.found.message', parametros: [
                        Message.getMensaje('sucursal.label', 'sucursal'), sucursalInstance.id
                ]))
            }
            sucursalInstance.nombre = sucursalMap.nombre
            sucursalInstance.clave = sucursalMap.clave
            sucursalInstance.direccion= direccionService.update(sucursalMap)

            this.save(sucursalInstance)
            log.info 'Plugin : appPuntos, Servicio : caja, Metodo : update Completado'
            return sucursalInstance
        } catch (Exception e) {
            log.error 'Plugin : appPuntos, Servicio : caja, Metodo : update, Error'
            throw e
        }
    }


}