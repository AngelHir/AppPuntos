package com.clases.app

class Producto {

    String nombre
    String descripcion
    int puntosRequeridos

    static constraints = {
        nombre nullable: false
        descripcion nullable: false
        puntosRequeridos nullable: false
    }
    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'producto_seq']
    }
}
