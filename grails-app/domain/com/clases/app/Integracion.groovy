package com.clases.app

class Integracion {

    String nombre
    String tipo

    static constraints = {
        nombre nullable: false
        tipo nullable: false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'integracion_seq']
    }
}
