package com.clases.app


class Cliente {

    static hasMany = [transacciones: Transaccion]
    Boolean activo=true
    String rfc
    Persona persona
    String numTarjeta
    String tipoMembresia
    int puntos=0

    static constraints = {
    numTarjeta Length:16, nullable: true, blank: false
    rfc nullable: true
    tipoMembresia nullable: true, blank: false
    puntos nullable: false, blank: false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'cliente_seq']
    }

}
