package com.clases.app

import com.software.componente.app.Message
import com.software.componente.app.ObjectException
import grails.converters.JSON


class ProductoController {

    ProductoService productoService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]



    def show(long id) {
        log.info 'Plugin : appPuntos, Controlador : Usuario, Accion : show'
        Producto productoInstance =productoService.get(id)
        render(contentType: "application/json") {
            id(productoInstance.id)
            nombre(productoInstance.nombre)
            descripcion(productoInstance.descripcion)
            codigoBarras(productoInstance.codigoBarra)
            stock(productoInstance.stock)
            precio(productoInstance.precio)
        }
    }

    /**
     * Controlador para la creacion de un nuevo Producto
     * @return Mapa con mensaje de exito de creacion
     * */
    def save() {
        log.info 'Plugin : AppPuntos, Controlador : Producto, Accion : save'
        try {
            Producto productoInstance = productoService.create(JSON.parse(request) as Map)
            render(contentType: "application/json") {
                success(
                        Message.getMensaje(codigo: 'default.created.message', parametros: [
                                Message.getMensaje('producto.label', 'Producto'),
                                productoInstance.id
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
     * Controlador para la actualizacion de Productos existentes
     * @return Mapa con mensaje de exito de actualizacion
     * */
    def update() {
        log.info 'Plugin : appPuntos, Controlador : Producto, Accion : update'
        try {
            Producto productoInstance = productoService.update(JSON.parse(request) as Map)
            render(contentType: "application/json") {
                success(
                        Message.getMensaje(codigo: 'default.updated.message', parametros: [
                                Message.getMensaje('producto.label', 'Producto'),
                                productoInstance.id
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
