package com.clases.app

import com.software.componente.app.Message
import com.software.componente.app.ObjectException
import grails.gorm.transactions.Transactional

@Transactional
class TransaccionService {

    /**
     * Busca y devuelve una instancia de la Transaccion mediante el id
     * @param id Identificador de la Transaccion
     * @return Instancia de la Transaccion encontrada
     */
    Transaccion get(long id) {
        log.info 'Plugin : AppPuntos, Servicio : Transaccion, Metodo : get'
        return Transaccion.get(id)
    }

    /**
     * Guarda en la base de datos los campos de una Transaccion, nuevo o actualizado
     * @param transaccionInstance Transaccion a actualizar o crear
     * @return Transaccion actualizado o creado
     * @throws com.software.componente.app.ObjectException Al encontrar algun error en los campos
     * */
    Transaccion save(Transaccion transaccionInstance) throws Exception {
        log.info 'Plugin : AppPuntos, Servicio : transaccion, Metodo : save'
        try {
            if (transaccionInstance.validate() && transaccionInstance.save()) {
                log.info 'Plugin : AppPuntos, Servicio : Transaccion, Metodo : save, Completado'
                return transaccionInstance
            }
            log.error 'Plugin : AppPuntos,  Servicio : transaccion, Metodo : save, Error en Guardado'
            throw new ObjectException(Message.getMensaje(codigo: 'save.fail', parametros: [
                    Message.getMensaje('transaccion.label', 'Transaccion')
            ]), transaccionInstance)
        }
        catch (org.springframework.dao.OptimisticLockingFailureException e) {
            throw new ObjectException(
                    Message.getMensaje([
                            codigo    : "default.optimistic.locking.failure",
                            parametros: [Message.getMensaje('transaccion.label', 'Transaccion')]
                    ]),
                    transaccionInstance, true
            )
        }
    }

    /**
     * Creacion de una nueva Transaccion
     * @param transaccionMap Datos necesarios para la creacion de una nueva Transaccion
     * @return Instancia de la Transaccion creada
     **/
    Transaccion create(Map transaccionMap) throws Exception {
        log.info 'Plugin : facturacionNomina, Servicio : Transaccion, Metodo : create Iniciando'
        try {
            Transaccion  transaccionInstance = new Transaccion()
            transaccionInstance.cliente = transaccionMap.usuario as Cliente
            transaccionInstance.sucursal = transaccionMap.sucursal as Sucursal
            transaccionInstance.tipo = transaccionMap.tipo
            transaccionInstance.descuento = transaccionMap.decuento as BigDecimal
            transaccionInstance.subtotal = transaccionMap.subtotal as BigDecimal
            transaccionInstance.total = transaccionMap.total as BigDecimal


            if (transaccionMap.inactivo) {
                transaccionInstance.activo = false
            }

            this.save(transaccionInstance)
            log.info 'Plugin : facturacionNomina, Servicio : Transaccion, Metodo : create Completado'
            return transaccionInstance
        }
        catch (Exception e) {
            log.error 'Plugin : facturacionNomina, Servicio : Transaccion, Metodo : create, Error'
            throw e
        }
    }


    /**
     * Actualizacion de una Transaccion existente
     * @param transaccionMap Datos necesarios para la actualizacion de la Transaccion
     * @return Instancia de la Transaccion actualizada
     * @throws RuntimeException Al no encontrar la Transaccion para actualizar
     **/
    Transaccion update(Map transaccionMap) throws Exception {
        log.info 'Plugin : appPuntos, Servicio : Transaccion, Metodo : update Iniciando'
        try {
            Transaccion transaccionInstance = this.get(transaccionMap.id as long)
            if (!transaccionInstance) {
                log.error 'Plugin : appPuntos, Servicio : transaccion, Metodo : update, Error No Encontrado'
                throw new RuntimeException(Message.getMensaje(codigo: 'default.not.found.message', parametros: [
                        Message.getMensaje('transaccion.label', 'Transaccion'), transaccionInstance.id
                ]))
            }
            transaccionInstance.cliente = transaccionMap.usuario as Cliente
            transaccionInstance.sucursal = transaccionMap.sucursal as Sucursal
            transaccionInstance.tipo = transaccionMap.tipo
            transaccionInstance.descuento = transaccionMap.decuento as BigDecimal
            transaccionInstance.subtotal = transaccionMap.subtotal as BigDecimal
            transaccionInstance.total = transaccionMap.total as BigDecimal



            this.save(transaccionInstance)
            log.info 'Plugin : appPuntos, Servicio : Transaccion, Metodo : update Completado'
            return transaccionInstance
        } catch (Exception e) {
            log.error 'Plugin : appPuntos, Servicio : Transaccion, Metodo : update, Error'
            throw e
        }
    }
}
