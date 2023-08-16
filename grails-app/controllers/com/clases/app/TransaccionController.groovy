package com.clases.app

import com.software.componente.app.Message
import com.software.componente.app.ObjectException
import grails.converters.JSON

class TransaccionController {

    TransaccionService transaccionService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]



    def show(long id) {
        log.info 'Plugin : appPuntos, Controlador :Transaccion, Accion : show'
        Transaccion transaccionInstance = transaccionService.get(id)
        render(contentType: "application/json") {
            id(transaccionInstance.id)
            cliente(transaccionInstance.cliente)
            fecha(transaccionInstance.fecha)
            tipo(transaccionInstance.tipo)
            sucursal(transaccionInstance.sucursal)
            total(transaccionInstance.total)
        }
    }

    /**
     * Controlador para la creacion de una nueva Transaccion
     * @return Mapa con mensaje de exito de creacion
     * */
    def save() {
        log.info 'Plugin : AppPuntos, Controlador : Transaccion , Accion : save'
        try {
            Transaccion transaccionInstance = transaccionService.create(JSON.parse(request) as Map)
            render(contentType: "application/json") {
                success(
                        Message.getMensaje(codigo: 'default.created.message', parametros: [
                                Message.getMensaje('transaccion.label', 'Transaccion'),
                                transaccionInstance.id
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
     * Controlador para la actualizacion de Transacciones existentes
     * @return Mapa con mensaje de exito de actualizacion
     * */
    def update() {
        log.info 'Plugin : appPuntos, Controlador : Transaccion, Accion : update'
        try {
            Transaccion transaccionInstance = transaccionService.update(JSON.parse(request) as Map)
            render(contentType: "application/json") {
                success(
                        Message.getMensaje(codigo: 'default.updated.message', parametros: [
                                Message.getMensaje('transaccion.label', 'Transaccion'),
                                transaccionInstance.id
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
