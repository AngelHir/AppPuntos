package com.clases.app


class Sesion {

    static belongsTo = [caja:Caja]

    static hasMany = [transacciones:Transaccion]

    Date fecha= new Date()

    Empleado empleado

    boolean activo = true

    BigDecimal saldoInicial

    int numVentas=0

    int totalVentas=0

    BigDecimal saldoFinal=saldoInicial


    static constraints = {
        fecha nullable: false, blank: false
        empleado nullable: false, blank:false
        numVentas nullanle: false, blank:false
        saldoInicial nullable:false, blank:false
        saldoFinal nullable: false, blank:false
        totalVentas nullable: false, blank: false
    }

    static mapping = {
        id generator: 'sequence', params: [ sequence_name:'sesion_seq']
    }
}
