package com.clases.app

import java.time.LocalTime

class Turno {

    LocalTime horaInicio
    LocalTime horaFin
    static hasMany = [sesiones: Sesion]
    static belongsTo = [caja: Caja]

    static constraints = {
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name:'turno_seq']
    }
}
