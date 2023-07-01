package com.clases.app

class Puntos {

    int cantidad
    Date fechaCaducidad
    Usuario usuario

    static constraints = {
        cantidad nullable: false
        fechaCaducidad nullable: false
        usuario nullable: false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'puntos_seq']
    }
}
