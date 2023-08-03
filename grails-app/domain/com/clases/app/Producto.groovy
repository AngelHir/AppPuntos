package com.clases.app

class Producto {

    Boolean activo=true
    String nombre
    String descripcion
    String codigoBarra
    int stock
    int precio

    static constraints = {
        nombre nullable: false
        descripcion nullable: false
        precio nullable: false
        codigoBarra nullable: false
    }
    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'producto_seq']
    }
}
