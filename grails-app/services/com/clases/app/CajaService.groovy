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

            this.save(usuarioInstance)
            log.info 'Plugin : facturacionNomina, Servicio : usuario, Metodo : create Completado'
            return usuarioInstance
        }
        catch (Exception e) {
            log.error 'Plugin : facturacionNomina, Servicio : usuario, Metodo : create, Error'
            throw e
        }
    }


    /**
     * Actualizacion de un Banco existente
     * @param usuarioMap Datos necesarios para la actualizacion del Usuario
     * @return Instancia del Usuario actualizado
     * @throws RuntimeException Al no encontrar el Usuario para actualizar
     **/
    Cliente update(Map usuarioMap) throws Exception {
        log.info 'Plugin : appPuntos, Servicio : usuario, Metodo : update Iniciando'
        try {
            Cliente usuarioInstance = this.get(usuarioMap.id as long)
            if (!usuarioInstance) {
                log.error 'Plugin : appPuntos, Servicio : Usuario, Metodo : update, Error No Encontrado'
                throw new RuntimeException(Message.getMensaje(codigo: 'default.not.found.message', parametros: [
                        Message.getMensaje('usuario.label', 'Usuario'), usuarioInstance.id
                ]))
            }
            usuarioInstance.persona= personaService.update(usuarioMap)
            usuarioInstance.numTarjeta = usuarioMap.nombre
            usuarioInstance.tipoMembresia = usuarioMap.direccion

            this.save(usuarioInstance)
            log.info 'Plugin : appPuntos, Servicio : Usuario, Metodo : update Completado'
            return usuarioInstance
        } catch (Exception e) {
            log.error 'Plugin : appPuntos, Servicio : Usuario, Metodo : update, Error'
            throw e
        }
    }
}
