package com.clases.app

import grails.converters.JSON

class NotificacionController {

    NotificacionService notificacionService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        def notificaciones = Notificacion.list()
        render(view: "index", model: [notificaciones: notificaciones])
    }

    def create() {
        try {
            def notificacionInstance = notificacionService.createNotificacion(params)
            redirect(action: "show", id: notificacionInstance.id)
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    def show() {
        def notificacionInstance = notificacionService.getNotificacion(params.id)
        render(view: "show", model: [notificacionInstance: notificacionInstance])
    }

    def edit() {
        def notificacionInstance = notificacionService.getNotificacion(params.id)
        render(view: "edit", model: [notificacionInstance: notificacionInstance])
    }

    def update() {
        try {
            def notificacionInstance = notificacionService.updateNotificacion(params.id, params)
            redirect(action: "show", id: notificacionInstance.id)
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    def delete() {
        notificacionService.deleteNotificacion(params.id)
        redirect(action: "index")
    }
}
