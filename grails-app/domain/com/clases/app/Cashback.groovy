package com.clases.app

class Cashback {

    Cliente cliente

    int puntosGenerados

    Transaccion transaccion


    static constraints = {
        cliente nullable: false, blank: false
        puntosGenerados nullable:false, blank: false
        transaccion nullable: false, blank : false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'cashback_seq']
    }
}
