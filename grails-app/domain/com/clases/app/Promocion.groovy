package com.clases.app

class Promocion {

    String nombre
    String descripcion
    String descuento
    Date fechaInicio
    Date fechaFinalizacion

    static constraints = {
        nombre nullable: false
        descripcion nullable: false
        descuento nullable: false
        fechaInicio nullable: false
        fechaFinalizacion nullable: false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'promocion_seq']
    }
}
