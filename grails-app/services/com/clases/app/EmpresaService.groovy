package com.clases.app

import com.software.componente.app.ObjectException
import com.software.componente.app.Message
import grails.gorm.transactions.Transactional

@Transactional
class EmpresaService {

    DireccionService direccionService

    /**
     * Busca y devuelve una instancia de la Empresa mediante el id
     * @param id Identificador de la Empresa
     * @return Instancia de la Empresa encontrada
     */
    Empresa get(long id) {
        log.info 'Plugin : AppPuntos, Servicio : caja, Metodo : get'
        return Empresa.get(id)
    }

    /**
     * Guarda en la base de datos los campos de una Empresa, nuevo o actualizado
     * @param empresaInstance Empresa a actualizar o crear
     * @return Empresa actualizada o creada
     * @throws com.software.componente.app.ObjectException Al encontrar algun error en los campos*/
    Empresa save(Empresa empresaInstance) throws Exception {
        log.info 'Plugin : AppPuntos, Servicio : empresa, Metodo : save'
        try {
            if (empresaInstance.validate() && empresaInstance.save()) {
                log.info 'Plugin : AppPuntos, Servicio : empresa, Metodo : save, Completado'
                return empresaInstance
            }
            log.error 'Plugin : AppPuntos,  Servicio : empresa, Metodo : save, Error en Guardado'
            throw new ObjectException(Message.getMensaje(codigo: 'save.fail', parametros: [
                    Message.getMensaje('empresa.label', 'Empresa')
            ]), empresaInstance)
        }
        catch (org.springframework.dao.OptimisticLockingFailureException e) {
            throw new ObjectException(
                    Message.getMensaje([
                            codigo    : "default.optimistic.locking.failure",
                            parametros: [Message.getMensaje('empresa.label', 'Empresa')]
                    ]),
                    empresaInstance, true
            )
        }
    }

    /**
     * Creacion de un nueva Empresa
     * @param empresaMap Datos necesarios para la creacion de la nueva Empresa
     * @return Instancia de la Empresa creada
     **/
    Empresa create(Map empresaMap) throws Exception {
        log.info 'Plugin : facturacionNomina, Servicio : empresa, Metodo : create Iniciando'
        try {
            Empresa empresaInstance = new Empresa()
            empresaInstance.direccion= direccionService.create(empresaMap)
            empresaInstance.nombre = empresaMap.nombre
            empresaInstance.razonSocial= empresaMap.razonSocial
            empresaInstance.rfc=empresaMap.rfc

            if (empresaMap.inactivo) {
                empresaInstance.activo = false
            }

            this.save(empresaInstance)
            log.info 'Plugin : facturacionNomina, Servicio : caja, Metodo : create Completado'
            return empresaInstance
        }
        catch (Exception e) {
            log.error 'Plugin : facturacionNomina, Servicio : caja, Metodo : create, Error'
            throw e
        }
    }


    /**
     * Actualizacion de una Empresa existente
     * @param empresaMap Datos necesarios para la actualizacion de la Empresa
     * @return Instancia de la Empresa actualizado
     * @throws RuntimeException Al no encontrar la Empresa para actualizar
     **/
    Empresa update(Map empresaMap) throws Exception {
        log.info 'Plugin : appPuntos, Servicio : empresa, Metodo : update Iniciando'
        try {
            Empresa empresaInstance = this.get(empresaMap.id as long)
            if (!empresaInstance) {
                log.error 'Plugin : appPuntos, Servicio : empresa, Metodo : update, Error No Encontrado'
                throw new RuntimeException(Message.getMensaje(codigo: 'default.not.found.message', parametros: [
                        Message.getMensaje('empresa.label', 'Empresa'), empresaInstance.id
                ]))
            }
            empresaInstance.direccion= direccionService.update(empresaMap)
            empresaInstance.nombre = empresaMap.nombre
            empresaInstance.razonSocial= empresaMap.razonSocial
            empresaInstance.rfc=empresaMap.rfc

            this.save(empresaInstance)
            log.info 'Plugin : appPuntos, Servicio : empresa, Metodo : update Completado'
            return empresaInstance
        } catch (Exception e) {
            log.error 'Plugin : appPuntos, Servicio : empresa , Metodo : update, Error'
            throw e
        }
    }


}