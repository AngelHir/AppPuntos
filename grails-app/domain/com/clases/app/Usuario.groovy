package com.clases.app

class Usuario {

    boolean activo=true
    String nombre
    String apellido
    String correoElectronico
    String numeroTelefono
    /**String direccion **/
    String numTarjeta
    String tipoMembresia

    static constraints = {
        nombre nullable: false
        correoElectronico nullable: false, email: true
        numeroTelefono nullable: true
        /**direccion nullable: true **/
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'usuario_seq']
    }

}
