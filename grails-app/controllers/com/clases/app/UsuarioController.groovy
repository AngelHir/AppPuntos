package com.clases.app

import com.software.componente.app.ObjectException
import com.software.componente.app.Message
import grails.converters.JSON


class UsuarioController {

    UsuarioService usuarioService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    /**
     * Controlador para la creacion de un nuevo Usuario
     * @return Mapa con mensaje de exito de creacion
     * */
    def save() {
        log.info 'Plugin : AppPuntos, Controlador : Usuario, Accion : save'
        try {
            Usuario usuarioInstance = usuarioService.create(JSON.parse(request) as Map)
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

    def show(long id) {
        log.info 'Plugin : AppPuntos, Controlador : Usuario, Accion : show'
        Usuario usuarioInstance = usuarioService.get(id)
        render(contentType: "application/json") {
            id(usuarioInstance.id)
            nombre(usuarioInstance.nombre)
            correoElectronico(usuarioInstance.correoElectronico)
            numeroTelefono(usuarioInstance.numeroTelefono)
            direccion(usuarioInstance.direccion)
            puntosAcumulados(usuarioInstance.puntosAcumulados)
        }
    }


}
