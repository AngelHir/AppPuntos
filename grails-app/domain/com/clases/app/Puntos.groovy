package com.clases.app

class Puntos {

    int cantidad=0
    Usuario usuario

    static constraints = {
        cantidad nullable: false
        usuario nullable: false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'puntos_seq']
    }
}
