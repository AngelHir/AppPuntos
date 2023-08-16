package com.clases.app

class Transaccion {

    static hasMany = [productos: CatalogoGeneral]
    static hasOne = [cashback:Cashback]
    static belongsTo = [sesion:Sesion]

        Date fecha = new Date()
        Boolean activo=true
        String tipo
        BigDecimal subtotal
        BigDecimal total
        Sucursal sucursal
        BigDecimal descuento
        Cliente cliente

    static constraints = {
        fecha nullable: false
        tipo nullable: false
        subtotal nullable: false
        total nullable: false
        descuento nullable : true
        sucursal nullable: false
        cliente nullable: false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'transaccion_seq']
    }
}
