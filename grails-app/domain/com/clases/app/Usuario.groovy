package com.clases.app

class Usuario {

    boolean activo=true
    String nombre
    String correoElectronico
    String contrasenia
    String numeroTelefono
    String direccion
    int puntosAcumulados=0

    static constraints = {
        nombre nullable: false
        correoElectronico nullable: false, email: true
        contrasenia nullable: false
        numeroTelefono nullable: true
        direccion nullable: true
        puntosAcumulados nullable: false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'usuario_seq']
    }

}
