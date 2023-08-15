package com.clases.app

class Caja {

    static belongsTo = [sucursal: Sucursal]

    static hasMany = [sesiones:Sesion,empleados:Empleado]

    String nombre




    static constraints = {
        nombre nullable: false, blank: false
    }

    static mapping = {
        id generator:'sequence',params: [sequence_name:'caja_seq']
    }
}
