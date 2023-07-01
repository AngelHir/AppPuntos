package com.clases.app

import grails.converters.JSON


class ProductoController {

    ProductoService productoService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        def productos = Producto.list()
        render(view: "index", model: [productos: productos])
    }

    def create() {
        try {
            def productoInstance = productoService.createProducto(params)
            redirect(action: "show", id: productoInstance.id)
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    def show() {
        def productoInstance = productoService.getProducto(params.id)
        render(view: "show", model: [productoInstance: productoInstance])
    }

    def edit() {
        def productoInstance = productoService.getProducto(params.id)
        render(view: "edit", model: [productoInstance: productoInstance])
    }

    def update() {
        try {
            def productoInstance = productoService.updateProducto(params.id, params)
            redirect(action: "show", id: productoInstance.id)
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    def delete() {
        productoService.deleteProducto(params.id)
        redirect(action: "index")
    }
}
