package com.clases.app

import com.software.componente.app.ObjectException
import com.software.componente.app.Message
import grails.converters.JSON

class CashbackController {

    CashbackService cashbackService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /**
     * Controlador para la creacion de un nuevo Cashback
     * @return Mapa con mensaje de exito de creacion
     * */
    def save() {
        log.info 'Plugin : AppPuntos, Controlador : cashback, Accion : save'
        try {
            Cashback cashbackInstance = cashbackService.create(JSON.parse(request) as Map)
            render(contentType: "application/json") {
                success(
                        Message.getMensaje(codigo: 'default.created.message', parametros: [
                                Message.getMensaje('cashback.label', 'Cashback'),
                                cashbackInstance.id
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
     * Controlador para la actualizacion de Cashbacks existente
     * @return Mapa con mensaje de exito de actualizacion
     * */
    def update() {
        log.info 'Plugin : appPuntos, Controlador : cashback, Accion : update'
        try {
            Cashback cashbackInstance = cashbackService.update(JSON.parse(request) as Map)
            render(contentType: "application/json") {
                success(
                        Message.getMensaje(codigo: 'default.updated.message', parametros: [
                                Message.getMensaje('cashback.label', 'Cashback'),
                                cashbackInstance.id
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
