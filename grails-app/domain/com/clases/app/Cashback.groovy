package com.clases.app

class Cashback {

    static belongsTo = [transaccion:Transaccion]

    Boolean activo= true

    Cliente cliente

    String tipo

    int puntosGenerados

    BigDecimal monto


    static constraints = {
        cliente nullable: false, blank: false
        puntosGenerados nullable:false, blank: false
        transaccion nullable: false, blank : false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'cashback_seq']
    }
}
