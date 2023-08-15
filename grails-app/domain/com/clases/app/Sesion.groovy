package com.clases.app


class Sesion {

    static belongsTo = [caja:Caja]

    static hasMany = [transacciones:Transaccion]

    Empleado empleado

    boolean activo = true



    static constraints = {
    }

    static mapping = {
        id generator: 'sequence', params: [ sequence_name:'sesion_seq']
    }
}
