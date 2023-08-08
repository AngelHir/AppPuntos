package com.clases.app

class Cliente {

    static hasMany = [transacciones: Transaccion]
    boolean activo=true
    String nombre
    String apellido
    String correoElectronico
    String numeroTelefono
    /**String direccion **/
    String numTarjeta
    String tipoMembresia
    int puntos=0

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
