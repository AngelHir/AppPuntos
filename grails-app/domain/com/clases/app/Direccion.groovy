package com.clases.app

class Direccion {

    String calle

    String numInterior

    String numExterior

    String ciudad

    String estado

    String codigoPostal

    static constraints = {

        calle nullable: false, blank: false
        numInterior nullable: true
        numExterior nullable: false, blank: false
        ciudad nullable: false, blank: false
        estado nullable: false, blank: false
        codigoPostal nullable: false, blank: false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'direccion_seq']
    }

}
