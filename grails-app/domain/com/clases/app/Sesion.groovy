package com.clases.app


class Sesion {

    Empleado empleado
    boolean atendido = false
    static belongsTo = [turno: Turno]


    static constraints = {
    }
}
