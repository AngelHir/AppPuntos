package com.clases.app


class Cliente {

    static hasMany = [transacciones: Transaccion]
    String numTarjeta
    String tipoMembresia
    int puntos=0

    static constraints = {
    numTarjeta Length:16, nullable: false, blank: false
    tipoMembresia nullable: false, blank: false
    puntos nullable: false, blank: false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'cliente_seq']
    }

}
