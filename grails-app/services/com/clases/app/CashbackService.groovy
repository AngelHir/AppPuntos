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
    Cashback
    create(Map cashbackMap) throws Exception {
        log.info 'Plugin : facturacionNomina, Servicio : cashback, Metodo : create Iniciando'
        try {
            Cashback cashbackInstance = new Cashback()
            cashbackInstance.cliente= cashbackMap.cliente as Cliente
            cashbackInstance.tipo= cashbackMap.tipo
            cashbackInstance.monto= cashbackMap.monto as BigDecimal
            cashbackInstance.puntosGenerados= cashbackMap.puntosGenerados as int


            if (cashbackMap.inactivo) {
                cashbackInstance.activo = false
            }

            this.save(cashbackInstance)
            log.info 'Plugin : facturacionNomina, Servicio : cashback, Metodo : create Completado'
            return cashbackInstance
        }
        catch (Exception e) {
            log.error 'Plugin : facturacionNomina, Servicio : cashback, Metodo : create, Error'
            throw e
        }
    }

    /**
     * Actualizacion de una Cashback existente
     * @param cashbackMap Datos necesarios para la actualizacion del Cashback
     * @return Instancia del Cashback actualizado
     * @throws RuntimeException Al no encontrar el Cashback para actualizar
     **/
    Cashback update(Map cashbackMap) throws Exception {
        log.info 'Plugin : appPuntos, Servicio : cashback, Metodo : update Iniciando'
        try {
            Cashback cashbackInstance = this.get(cashbackMap.id as long)
            if (!cashbackInstance) {
                log.error 'Plugin : appPuntos, Servicio : cashback, Metodo : update, Error No Encontrado'
                throw new RuntimeException(Message.getMensaje(codigo: 'default.not.found.message', parametros: [
                        Message.getMensaje('cashback.label', 'Cashback'), cashbackInstance.id
                ]))
            }
            cashbackInstance.cliente = cashbackMap.cliente as Cliente
            cashbackInstance.tipo= cashbackMap.tipo


            this.save(cashbackInstance)
            log.info 'Plugin : appPuntos, Servicio : cashback, Metodo : update Completado'
            return cashbackInstance
        } catch (Exception e) {
            log.error 'Plugin : appPuntos, Servicio : cashback, Metodo : update, Error'
            throw e
        }
    }
}



