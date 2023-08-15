package com.clases.app

class Caja {

    static belongsTo = [sucursal: Sucursal]

    static hasMany = [sesiones:Sesion,empleados:Empleado]

    String nombre




    static constraints = {
    }
}
