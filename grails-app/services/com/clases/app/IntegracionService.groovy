package com.clases.app

import grails.gorm.transactions.Transactional

@Transactional
class IntegracionService {
    def createIntegracion(params) {
        def integracionInstance = new Integracion(params)
        if (integracionInstance.save()) {
            return integracionInstance
        } else {
            throw new RuntimeException("Error al crear la integración")
        }
    }

    def getIntegracion(id) {
        return Integracion.get(id)
    }

    def updateIntegracion(id, params) {
        def integracionInstance = Integracion.get(id)
        integracionInstance.properties = params
        if (integracionInstance.save()) {
            return integracionInstance
        } else {
            throw new RuntimeException("Error al actualizar la integración")
        }
    }

    def deleteIntegracion(id) {
        def integracionInstance = Integracion.get(id)
        integracionInstance.delete()
    }
}
