package com.clases.app

import grails.gorm.transactions.Transactional

@Transactional
class ProductoService {

def createProducto(params) {
    def productoInstance = new Producto(params)
    if (productoInstance.save()) {
        return productoInstance
    } else {
        throw new RuntimeException("Error al crear el producto")
    }
}

def getProducto(id) {
    return Producto.get(id)
}

def updateProducto(id, params) {
    def productoInstance = Producto.get(id)
    productoInstance.properties = params
    if (productoInstance.save()) {
        return productoInstance
    } else {
        throw new RuntimeException("Error al actualizar el producto")
    }
}

def deleteProducto(id) {
    def productoInstance = Producto.get(id)
    productoInstance.delete()
}
}
