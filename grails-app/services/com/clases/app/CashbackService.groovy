package com.clases.app

import com.software.componente.app.Message
import com.software.componente.app.ObjectException
import grails.gorm.transactions.Transactional

@Transactional
class CashbackService {

    /**
     * Busca y devuelve una instancia del Cashback mediante el id
     * @param id Identificador del usuario
     * @return Instancia del Cashback encontrada
     */
    Cashback get(long id) {
        log.info 'Plugin : AppPuntos, Servicio : cashback, Metodo : get'
        return Cashback.get(id)
    }

    /**
     * Guarda en la base de datos los campos del Cashback nuevo o actualizado
     * @param cashbackInstance Cashback a actualizar o crear
     * @return Cashback actualizada o creada
     * @throws com.software.componente.app.ObjectException Al encontrar algun error en los campos
     * */
    Cashback save(Cashback cashbackInstance) throws Exception {
        log.info 'Plugin : AppPuntos, Servicio : cashback, Metodo : save'
        try {
            if (cashbackInstance.validate() && cashbackInstance.save()) {
                log.info 'Plugin : AppPuntos, Servicio : cashback, Metodo : save, Completado'
                return cashbackInstance
            }
            log.error 'Plugin : AppPuntos,  Servicio : cashback, Metodo : save, Error en Guardado'
            throw new ObjectException(Message.getMensaje(codigo: 'save.fail', parametros: [
                    Message.getMensaje('cashback.label', 'Cashback')
            ]), cashbackInstance)
        }
        catch (org.springframework.dao.OptimisticLockingFailureException e) {
            throw new ObjectException(
                    Message.getMensaje([
                            codigo    : "default.optimistic.locking.failure",
                            parametros: [Message.getMensaje('cashback.label', 'Cashback')]
                    ]),
                    cashbackInstance, true
            )
        }
    }

    /**
     * Creacion de un nuevo Cashback
     * @param cashbackMap Datos necesarios para la creacion del nuevo Cashback
     * @return Instancia del Cashback creado
     **/
    Cashback create(Map cashbackMap) throws Exception {
        log.info 'Plugin : facturacionNomina, Servicio : cashback, Metodo : create Iniciando'
        try {
            Cashback cashbackInstance = new Cashback()


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



