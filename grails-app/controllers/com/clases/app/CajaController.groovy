package com.clases.app

import com.software.componente.app.ObjectException
import com.software.componente.app.Message
import grails.converters.JSON

class CajaController {

    CajaService cajaService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    /**
     * Controlador para la creacion de una nuevo Caja
     * @return Mapa con mensaje de exito de creacion
     * */
    def save() {
        log.info 'Plugin : AppPuntos, Controlador : caja, Accion : save'
        try {
            Caja cajaInstance = cajaService.create(JSON.parse(request) as Map)
            render(contentType: "application/json") {
                success(
                        Message.getMensaje(codigo: 'default.created.message', parametros: [
                                Message.getMensaje('caja.label', 'Caja'),
                                cajaInstance.id
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
     * Controlador para la actualizacion de usuarios existentes
     * @return Mapa con mensaje de exito de actualizacion
     * */
    def update() {
        log.info 'Plugin : appPuntos, Controlador : caja, Accion : update'
        try {
            Caja cajaInstance = cajaService.update(JSON.parse(request) as Map)
            render(contentType: "application/json") {
                success(
                        Message.getMensaje(codigo: 'default.updated.message', parametros: [
                                Message.getMensaje('caja.label', 'Caja'),
                                cajaInstance.id
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
