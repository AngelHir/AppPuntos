package com.software.componente.app

class ObjectException extends RuntimeException {
    def instance
    Map responseObject = [:]
/***Metodo para control devolucion de errores de la instancia**/

    ObjectException(String message, def instance) {
        super(message)
        this.instance = instance
        responseObject << [error: FormValidation.formUnitarioMap(instance), message: message]

    }
/***Metodo concurrente para control de version y devolucion de la instancia actualizada**/
    ObjectException(String message, def instance, boolean optimisticLockingFailureException) {
        super(message)
        this.instance = instance
        this.responseObject << [instance: instance.obtieneDatos(), optimisticLockingException: true, message: message]
    }
/**Metodo que solo devuelve un mensaje de errors
 * **/
    ObjectException(String message) {
        super(message)
    }
}

