package com.clases.app

class Transaccion {

    Date fecha
    Boolean activo=true
    String tipo
    Producto productos
    BigDecimal monto
    String local
    int descuento
    Usuario usuario
    int puntosGenerados

    static constraints = {
        fecha nullable: false
        tipo nullable: false
        monto nullable: false
        usuario nullable: false
        puntosGenerados nullable: true
        descuento nullable : true
        local nullable: false
        productos nullable: false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'transaccion_seq']
    }
}
