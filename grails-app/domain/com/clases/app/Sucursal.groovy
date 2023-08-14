package com.clases.app

class Sucursal {

    String clave

    Direccion direccion

    static constraints = {
    }

    static mapping = {
        id generator:'sequence',params: [sequence_name: 'sucursal_seq']
    }
}
