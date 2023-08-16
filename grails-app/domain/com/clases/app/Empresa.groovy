package com.clases.app

class Empresa {

    static hasMany = [sucursales:Sucursal]
    String nombre
    String rfc
    Direccion direccion
    String razonSocial


    static constraints = {
        nombre nullable: false, blank: false, unique: true
        rfc nullable: false, blank: false, unique: true, lengh:12
        razonSocial nullable: false, blank: false

    }

    static mapping = {

        id generator:'sequence', params: [sequence_name:'empresa_sequence']
    }
}
