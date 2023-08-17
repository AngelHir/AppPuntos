package com.clases.app

import com.software.componente.app.ObjectException
import com.software.componente.app.Message
import grails.gorm.transactions.Transactional

@Transactional
class ClienteService {

    PersonaService personaService

    /**
     * Busca y devuelve una instancia del Cliente mediante el id
     * @param id Identificador del cliente
     * @return Instancia del Cliente encontrada
     */
    Cliente get(long id) {
        log.info 'Plugin : AppPuntos, Servicio : cliente, Metodo : get'
        return Cliente.get(id)
    }

    /**
     * Guarda en la base de datos los campos de un Cliente, nuevo o actualizado
     * @param clienteInstance Cliente a actualizar o crear
     * @return Cliente actualizado o creado
     * @throws ObjectException Al encontrar algun error en los campos*/
    Cliente save(Cliente clienteInstance) throws Exception {
        log.info 'Plugin : AppPuntos, Servicio : cliente, Metodo : save'
        try {
            if (clienteInstance.validate() && clienteInstance.save()) {
                log.info 'Plugin : AppPuntos, Servicio : cliente, Metodo : save, Completado'
                return clienteInstance
            }
            log.error 'Plugin : AppPuntos,  Servicio : cliente, Metodo : save, Error en Guardado'
            throw new ObjectException(Message.getMensaje(codigo: 'save.fail', parametros: [
                    Message.getMensaje('cliente.label', 'Cliente')
            ]), clienteInstance)
        }
        catch (org.springframework.dao.OptimisticLockingFailureException e) {
            throw new ObjectException(
                    Message.getMensaje([
                            codigo    : "default.optimistic.locking.failure",
                            parametros: [Message.getMensaje('cliente.label', 'Cliente')]
                    ]),
                    clienteInstance, true
            )
        }
    }

    /**
     * Creacion de un nuevo Cliente
     * @param clienteMap Datos necesarios para la creacion del nuevo Cliente
     * @return Instancia del Cliente creado
     **/
    Cliente create(Map clienteMap) throws Exception {
        log.info 'Plugin : facturacionNomina, Servicio :cliente, Metodo : create Iniciando'
        try {
            Cliente clienteInstance = new Cliente()
            clienteInstance.persona= personaService.create(clienteMap)
            clienteInstance.numTarjeta = clienteMap.numTarjeta
            clienteInstance.tipoMembresia = clienteMap.tipoMembresia

            if (clienteMap.inactivo) {
                clienteInstance.activo = false
            }

            this.save(clienteInstance)
            log.info 'Plugin : facturacionNomina, Servicio : cliente, Metodo : create Completado'
            return clienteInstance
        }
        catch (Exception e) {
            log.error 'Plugin : facturacionNomina, Servicio : cliente, Metodo : create, Error'
            throw e
        }
    }


    /**
     * Actualizacion de un Cliente existente
     * @param clienteMap Datos necesarios para la actualizacion del Cliente
     * @return Instancia del Cliente actualizado
     * @throws RuntimeException Al no encontrar el Cliente para actualizar
     **/
    Cliente update(Map clienteMap) throws Exception {
        log.info 'Plugin : appPuntos, Servicio : cliente, Metodo : update Iniciando'
        try {
            Cliente clienteInstance = this.get(clienteMap.id as long)
            if (!clienteInstance) {
                log.error 'Plugin : appPuntos, Servicio : cliente, Metodo : update, Error No Encontrado'
                throw new RuntimeException(Message.getMensaje(codigo: 'default.not.found.message', parametros: [
                        Message.getMensaje('cliente.label', 'Cliente'), clienteInstance.id
                ]))
            }
            clienteInstance.persona= personaService.update(clienteMap)
            clienteInstance.numTarjeta = clienteMap.nombre
            clienteInstance.tipoMembresia = clienteMap.direccion

            this.save(clienteInstance)
            log.info 'Plugin : appPuntos, Servicio : cliente, Metodo : update Completado'
            return clienteInstance
        } catch (Exception e) {
            log.error 'Plugin : appPuntos, Servicio : cliente, Metodo : update, Error'
            throw e
        }
    }


}
