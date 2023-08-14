package com.clases.app

class Empresa {

    String nombre
    String rfc
    Direccion direccion

    static constraints = {

    }

    static mapping = {

        id generator:'sequence', params: [sequence_name:'empresa_sequence']
    }
}
