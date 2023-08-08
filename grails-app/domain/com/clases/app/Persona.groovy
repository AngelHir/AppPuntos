package com.clases.app

class Persona {

    String nombre
    String apellido
    String email
    String telefono

    static constraints = {
        nombre nullable: false, blank: false
        apellido nullable: false, blank: false
        email nullable: false, blank: false
        telefono nullable: false, blank: false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'persona_seq']
    }
}
