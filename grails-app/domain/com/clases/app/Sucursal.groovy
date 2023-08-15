package com.clases.app

class Sucursal {

    static belongsTo = [empresa:Empresa]

    static hasMany = [cajas:Caja]

    String nombre

    String clave

    Direccion direccion


    static constraints = {
        nombre nullable: false, blank: false
        clave lengt:3, nullable: false, blank: false
    }

    static mapping = {
        id generator:'sequence',params: [sequence_name: 'sucursal_seq']
    }
}
