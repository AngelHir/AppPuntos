package com.clases.app

import com.software.componente.app.ObjectException
import com.software.componente.app.Message
import grails.converters.JSON

class ProductoController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    ProductoService productoService

    /**
     * Controlador para la creacion de un nuevo Producto
     * @return Mapa con mensaje de exito de creacion
     * */
    def save() {
        log.info 'Plugin : AppPuntos, Controlador : catalogo, Accion : save'
        try {
            Producto catalogoInstance = productoService.create(JSON.parse(request) as Map)
            render(contentType: "application/json") {
                success(
                        Message.getMensaje(codigo: 'default.created.message', parametros: [
                                Message.getMensaje('catalogo.label', 'Catalogo'),
                                catalogoInstance.id
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
     * Controlador para la actualizacion de Catalogos existentes
     * @return Mapa con mensaje de exito de actualizacion
     * */
    def update() {
        log.info 'Plugin : appPuntos, Controlador : catalogo, Accion : update'
        try {
            Producto catalogoInstance = productoService.update(JSON.parse(request) as Map)
            render(contentType: "application/json") {
                success(
                        Message.getMensaje(codigo: 'default.updated.message', parametros: [
                                Message.getMensaje('catalogo.label', 'Catalogo'),
                                catalogoInstance.id
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
