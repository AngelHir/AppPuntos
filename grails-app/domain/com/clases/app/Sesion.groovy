package com.clases.app


class Sesion {

    Empleado empleado
    boolean atendido = false
    static belongsTo = [turno: Turno]


    static constraints = {
    }

    static mapping = {
        id generator: 'sequence', params: [ sequence_name:'sesion_seq']
    }
}
