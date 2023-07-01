package com.clases.app

import grails.gorm.transactions.Transactional

@Transactional
class TransaccionService {

    def createTransaccion(params) {
        def transaccionInstance = new Transaccion(params)
        if (transaccionInstance.save()) {
            return transaccionInstance
        } else {
            throw new RuntimeException("Error al crear la transacción")
        }
    }

    def getTransaccion(id) {
        return Transaccion.get(id)
    }

    def updateTransaccion(id, params) {
        def transaccionInstance = Transaccion.get(id)
        transaccionInstance.properties = params
        if (transaccionInstance.save()) {
            return transaccionInstance
        } else {
            throw new RuntimeException("Error al actualizar la transacción")
        }
    }

    def deleteTransaccion(id) {
        def transaccionInstance = Transaccion.get(id)
        transaccionInstance.delete()
    }
}
