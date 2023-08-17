package com.clases.app


class Sesion {

    static belongsTo = [caja:Caja]

    static hasMany = [transacciones:Transaccion]

    Empleado empleado

    boolean activo = true

    BigDecimal saldoInicial

    int numVentas=0

    BigDecimal saldoFinal


    static constraints = {

        empleado nullable: false, blank:false
        numVentas blank:false
        saldoInicial nullable:false, blank:false
        saldoFinal nullable: false, blank:false
    }

    static mapping = {
        id generator: 'sequence', params: [ sequence_name:'sesion_seq']
    }
}
