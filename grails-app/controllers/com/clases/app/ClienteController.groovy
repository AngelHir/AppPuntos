package com.clases.app

import com.software.componente.app.ObjectException
import com.software.componente.app.Message
import grails.converters.JSON

class ClienteController {

    ClienteService clienteService

    DireccionService direccionService

    PersonaService personaService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


  /**
    def show(long id) {
        log.info 'Plugin : appPuntos, Controlador : Usuario, Accion : show'
        Cliente usuarioInstance = clienteService.get(id)
        Persona personaInstance= personaService.get(id)
        Direccion direccionInstance= direccionService.get(id)
        render(contentType: "application/json") {
            id(usuarioInstance.id)
            nombre(personaInstance.nombre)
            apellido(personaInstance.apellido)
            puntosAcumulados(usuarioInstance.puntos)
            numTarjeta(usuarioInstance.numTarjeta)
            tipoMembresia(usuarioInstance.tipoMembresia)
        }
    }
   **/

    /**
     * Controlador para la creacion de un nuevo Cliente
     * @return Mapa con mensaje de exito de creacion
     * */
    def save() {
        log.info 'Plugin : AppPuntos, Controlador : cliente, Accion : save'
        try {
            Cliente clienteInstance = clienteService.create(JSON.parse(request) as Map)
            render(contentType: "application/json") {
                success(
                        Message.getMensaje(codigo: 'default.created.message', parametros: [
                                Message.getMensaje('cliente.label', 'Cliente'),
                                clienteInstance.id
                        ])
                )
            }
        } catch (ObjectException e) {
            render(status: 404, e.responseObject as JSON, contentType: "application/json")
        } catch (Exception e) {
            render(status: 404, contentType: "application/json") { mensajeError(e.getMessage()) }
        }
    }

    /**
     * Controlador para la actualizacion de Clientes existentes
     * @return Mapa con mensaje de exito de actualizacion
     * */
        def update() {
            log.info 'Plugin : appPuntos, Controlador : cliente, Accion : update'
            try {
                Cliente clienteInstance = clienteService.update(JSON.parse(request) as Map)
                render(contentType: "application/json") {
                    success(
                            Message.getMensaje(codigo: 'default.updated.message', parametros: [
                                    Message.getMensaje('cliente.label', 'Cliente'),
                                    clienteInstance.id
                            ])
                    )
                }
            } catch (ObjectException e) {
                render(status: 404, e.responseObject as JSON, contentType: "application/json")
            } catch (Exception e) {
                render(status: 404, contentType: "application/json") {
                    mensajeError(e.getMessage())
                }
            }
        }


}
