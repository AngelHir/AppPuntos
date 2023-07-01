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

    def create() {
        try {
            def usuarioInstance = usuarioService.createUsuario(params)
            redirect(action: "show", id: usuarioInstance.id)
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    def show() {
        def usuarioInstance = usuarioService.getUsuario(params.id)
        render(view: "show", model: [usuarioInstance: usuarioInstance])
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
