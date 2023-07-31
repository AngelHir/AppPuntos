package com.clases.app

class Transaccion {

    Date fecha
    Boolean activo=true
    String tipo
    String descripcion
    BigDecimal monto
    Usuario usuario

    static constraints = {
        fecha nullable: false
        tipo nullable: false
        descripcion nullable: false
        monto nullable: false
        usuario nullable: false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'transaccion_seq']
    }
}
