package com.clases.app

class Caja {

    String nombre
    static hasMany = [turnos: Turno]

    static constraints = {
    }
}
