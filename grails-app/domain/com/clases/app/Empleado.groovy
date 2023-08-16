package com.clases.app


class Empleado {

    Date ingreso

    BigDecimal salario

    String puesto

    Sucursal sucursal

    Persona persona



    static constraints = {
        ingreso nullable: false, blank: false
        salario nullable: false, blank: false
        puesto nullable: false, blank: false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'empleado_seq']
    }
}
