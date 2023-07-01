package com.clases.app

class Notificacion {

    String texto
    Date fechaEnvio
    Usuario usuario

    static constraints = {
        texto nullable: false
        fechaEnvio nullable: false
        usuario nullable: false
    }
    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'notificacion_seq']
    }
}

