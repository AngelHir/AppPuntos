package com.clases.app

import grails.converters.JSON

class PuntosController {

    PuntosService puntosService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

        def index() {
            def puntos = Puntos.list()
            render(view: "index", model: [puntos: puntos])
        }

        def create() {
            try {
                def puntosInstance = puntosService.createPuntos(params)
                redirect(action: "show", id: puntosInstance.id)
            } catch (Exception e) {
                Map error = [error: e.getMessage()]
                render error as JSON
            }
        }

        def show() {
            def puntosInstance = puntosService.getPuntos(params.id)
            render(view: "show", model: [puntosInstance: puntosInstance])
        }

        def edit() {
            def puntosInstance = puntosService.getPuntos(params.id)
            render(view: "edit", model: [puntosInstance: puntosInstance])
        }

        def update() {
            try {
                def puntosInstance = puntosService.updatePuntos(params.id, params)
                redirect(action: "show", id: puntosInstance.id)
            } catch (Exception e) {
                Map error = [error: e.getMessage()]
                render error as JSON
            }
        }

        def delete() {
            puntosService.deletePuntos(params.id)
            redirect(action: "index")
        }
    }
