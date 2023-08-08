package com.clases.app

class Transaccion {

    static hasMany = [productos: Producto]
    Date fecha = new Date()
    Boolean activo=true
    String tipo
    BigDecimal monto
    String local
    int descuento
    int puntosGenerados
    Cliente usuario

    static constraints = {
        fecha nullable: false
        tipo nullable: false
        monto nullable: false
        puntosGenerados nullable: true
        descuento nullable : true
        local nullable: false
        productos nullable: false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'transaccion_seq']
    }
}
