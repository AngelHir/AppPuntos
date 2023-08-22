package com.clases.app

class Producto {

    Boolean activo=true
    String nombre
    String descripcion
    String codigo
    String clasificacion
    BigDecimal precio
    int disponibilidad
    String proveedor


    static constraints = {
        nombre nullable: false, blank: false
        descripcion nullable: false, blank: false
        codigo length:8, nullable: false, blank: false, unique: true
        clasificacion nullable: false, blank: false
        precio nullable: false, blank: false
        disponibilidad nullable: false, blank: false
        proveedor nullable: false, blank: false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'catalogo_general_seq']
    }
}
