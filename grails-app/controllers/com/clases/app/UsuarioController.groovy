package com.clases.app

import grails.converters.JSON

class UsuarioController {

    UsuarioService usuarioService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        def usuarios = Usuario.list()
        render(view: "index", model: [usuarios: usuarios])
    }

    /**
     * Controlador para la creacion de un nuevo Banco
     * @return Mapa con mensaje de exito de creacion
     * */
    def save() {
        log.info 'Plugin : AppPuntos, Controlador : Usuario, Accion : save'
        try {
            Usuario usuarioInstance = usuarioService.create(JSON.parse(request))
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

    def edit() {
        def usuarioInstance = usuarioService.getUsuario(params.id)
        render(view: "edit", model: [usuarioInstance: usuarioInstance])
    }

    def update() {
        try {
            def usuarioInstance = usuarioService.updateUsuario(params.id, params)
            redirect(action: "show", id: usuarioInstance.id)
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    def delete() {
        usuarioService.deleteUsuario(params.id)
        redirect(action: "index")
    }
}
