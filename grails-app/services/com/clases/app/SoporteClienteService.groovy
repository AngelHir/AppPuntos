package com.clases.app

import grails.gorm.transactions.Transactional

@Transactional
class SoporteClienteService {

    def createSoporteCliente(params) {
        def soporteInstance = new SoporteCliente(params)
        if (soporteInstance.save()) {
            return soporteInstance
        } else {
            throw new RuntimeException("Error al crear el soporte del cliente")
        }
    }

    def getSoporteCliente(id) {
        return SoporteCliente.get(id)
    }

    def updateSoporteCliente(id, params) {
        def soporteInstance = SoporteCliente.get(id)
        soporteInstance.properties = params
        if (soporteInstance.save()) {
            return soporteInstance
        } else {
            throw new RuntimeException("Error al actualizar el soporte del cliente")
        }
    }

    def deleteSoporteCliente(id) {
        def soporteInstance = SoporteCliente.get(id)
        soporteInstance.delete()
    }
}
