package com.clases.app

import grails.converters.JSON

class PromocionController {

    PromocionService promocionService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        def promociones = Promocion.list()
        render(view: "index", model: [promociones: promociones])
    }

    def create() {
        try {
            def promocionInstance = promocionService.createPromocion(params)
            redirect(action: "show", id: promocionInstance.id)
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    def show() {
        def promocionInstance = promocionService.getPromocion(params.id)
        render(view: "show", model: [promocionInstance: promocionInstance])
    }

    def edit() {
        def promocionInstance = promocionService.getPromocion(params.id)
        render(view: "edit", model: [promocionInstance: promocionInstance])
    }

    def update() {
        try {
            def promocionInstance = promocionService.updatePromocion(params.id, params)
            redirect(action: "show", id: promocionInstance.id)
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    def delete() {
        promocionService.deletePromocion(params.id)
        redirect(action: "index")
    }
}
