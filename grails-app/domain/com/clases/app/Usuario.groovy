package com.clases.app

class Usuario {

    boolean activo=true
    String nombre
    String correoElectronico
    String contrasenia
    String numeroTelefono
    String direccion
    int puntosAcumulados

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


    /**
     * Datos generales
     * @return Mapa con datos generales
     */
    Map obtieneDatos() {
        return  [
                id              : id,
                nombre          : nombre,
                correo          : correoElectronico,
                telefono        : numeroTelefono,
                direccion       : direccion,
                puntos          : puntosAcumulados
        ]
    }
}
