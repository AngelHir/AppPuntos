package com.clases.app

class Sucursal {

    String nombre

    String clave

    Direccion direccion

    Empresa empresa

    static constraints = {
        nombre nullable: false, blank: false
        clave lengt:3, nullable: false, blank: false
    }

    static mapping = {
        id generator:'sequence',params: [sequence_name: 'sucursal_seq']
    }
}
