package com.clases.app

import grails.converters.JSON


class SoporteClienteController {

    SoporteClienteService soporteClienteService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        def soportes = SoporteCliente.list()
        render(view: "index", model: [soportes: soportes])
    }

    def create() {
        try {
            def soporteInstance = soporteClienteService.createSoporteCliente(params)
            redirect(action: "show", id: soporteInstance.id)
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    def show() {
        def soporteInstance = soporteClienteService.getSoporteCliente(params.id)
        render(view: "show", model: [soporteInstance: soporteInstance])
    }

    def edit() {
        def soporteInstance = soporteClienteService.getSoporteCliente(params.id)
        render(view: "edit", model: [soporteInstance: soporteInstance])
    }

    def update() {
        try {
            def soporteInstance = soporteClienteService.updateSoporteCliente(params.id, params)
            redirect(action: "show", id: soporteInstance.id)
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    def delete() {
        soporteClienteService.deleteSoporteCliente(params.id)
        redirect(action: "index")
    }
}
