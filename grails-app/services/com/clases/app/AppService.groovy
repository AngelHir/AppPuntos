package com.clases.app

import grails.gorm.transactions.Transactional

@Transactional
class AppService {

    def crearProductos() {
        new Producto(nombre: 'Galletas Oreo 60 grms', descripcion: 'unidad de galleta oreo marca Nacional de 60 grms', codigoBarra:'00001x45',
                stock: 12, precio: 18.50 ).save(flush: true)
    }
}
