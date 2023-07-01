package com.clases.app

class SoporteCliente {

    String descripcionProblema
    String solucionPropuesta
    Usuario usuario

    static constraints = {
        descripcionProblema nullable: false
        solucionPropuesta nullable: true
        usuario nullable: false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'soporteCliente_seq']
    }
}
