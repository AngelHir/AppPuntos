package com.clases.app

class Empresa {

    static hasMany = [sucursales:Sucursal]
    String nombre
    String rfc
    Direccion direccion
    String razonSocial


    static constraints = {

    }

    static mapping = {

        id generator:'sequence', params: [sequence_name:'empresa_sequence']
    }
}
