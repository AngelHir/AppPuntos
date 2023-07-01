package com.clases.app

import grails.converters.JSON

class TransaccionController {

    TransaccionService transaccionService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        def transacciones = Transaccion.list()
        render(view: "index", model: [transacciones: transacciones])
    }

    def create() {
        try {
            def transaccionInstance = transaccionService.createTransaccion(params)
            redirect(action: "show", id: transaccionInstance.id)
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    def show() {
        def transaccionInstance = transaccionService.getTransaccion(params.id)
        render(view: "show", model: [transaccionInstance: transaccionInstance])
    }

    def edit() {
        def transaccionInstance = transaccionService.getTransaccion(params.id)
        render(view: "edit", model: [transaccionInstance: transaccionInstance])
    }

    def update() {
        try {
            def transaccionInstance = transaccionService.updateTransaccion(params.id, params)
            redirect(action: "show", id: transaccionInstance.id)
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    def delete() {
        transaccionService.deleteTransaccion(params.id)
        redirect(action: "index")
    }
}
