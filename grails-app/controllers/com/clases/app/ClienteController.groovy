package com.clases.app

import com.software.componente.app.ObjectException
import com.software.componente.app.Message
import grails.converters.JSON

class ClienteController {

    ClienteService clienteService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


/**
    def show(long id) {
        log.info 'Plugin : appPuntos, Controlador : Usuario, Accion : show'
        Cliente usuarioInstance = clienteService.get(id)
        render(contentType: "application/json") {
            id(usuarioInstance.id)
            nombre(usuarioInstance.nombre)
            correoElectronico(usuarioInstance.correoElectronico)
            numeroTelefono(usuarioInstance.numeroTelefono)
            apellido(usuarioInstance.apellido)
            puntosAcumulados(usuarioInstance.puntos)
            numTarjeta(usuarioInstance.numTarjeta)
            tipoMembresia(usuarioInstance.tipoMembresia)
        }
    }
 **/

    /**
     * Controlador para la creacion de un nuevo Usuario
     * @return Mapa con mensaje de exito de creacion
     * */
    def save() {
        log.info 'Plugin : AppPuntos, Controlador : Usuario, Accion : save'
        try {
            Cliente usuarioInstance = clienteService.create(JSON.parse(request) as Map)
            render(contentType: "application/json") {
                success(
                        Message.getMensaje(codigo: 'default.created.message', parametros: [
                                Message.getMensaje('usuario.label', 'Usuario'),
                                usuarioInstance.id
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
     * Controlador para la actualizacion de usuarios existentes
     * @return Mapa con mensaje de exito de actualizacion
     * */
        def update() {
            log.info 'Plugin : appPuntos, Controlador : Usuario, Accion : update'
            try {
                Cliente usuarioInstance = clienteService.update(JSON.parse(request) as Map)
                render(contentType: "application/json") {
                    success(
                            Message.getMensaje(codigo: 'default.updated.message', parametros: [
                                    Message.getMensaje('usuario.label', 'Usuario'),
                                    usuarioInstance.id
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
