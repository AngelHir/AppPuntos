package com.clases.app

import grails.converters.JSON

class IntegracionController {

    IntegracionService integracionService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        def integraciones = Integracion.list()
        render(view: "index", model: [integraciones: integraciones])
    }

    def create() {
        try {
            def integracionInstance = integracionService.createIntegracion(params)
            redirect(action: "show", id: integracionInstance.id)
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    def show() {
        def integracionInstance = integracionService.getIntegracion(params.id)
        render(view: "show", model: [integracionInstance: integracionInstance])
    }

    def edit() {
        def integracionInstance = integracionService.getIntegracion(params.id)
        render(view: "edit", model: [integracionInstance: integracionInstance])
    }

    def update() {
        try {
            def integracionInstance = integracionService.updateIntegracion(params.id, params)
            redirect(action: "show", id: integracionInstance.id)
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    def delete() {
        integracionService.deleteIntegracion(params.id)
        redirect(action: "index")
    }
}
