package com.clases.app

import grails.gorm.transactions.Transactional

@Transactional
class PromocionService {

    def createPromocion(params) {
        def promocionInstance = new Promocion(params)
        if (promocionInstance.save()) {
            return promocionInstance
        } else {
            throw new RuntimeException("Error al crear la promoción")
        }
    }

    def getPromocion(id) {
        return Promocion.get(id)
    }

    def updatePromocion(id, params) {
        def promocionInstance = Promocion.get(id)
        promocionInstance.properties = params
        if (promocionInstance.save()) {
            return promocionInstance
        } else {
            throw new RuntimeException("Error al actualizar la promoción")
        }
    }

    def deletePromocion(id) {
        def promocionInstance = Promocion.get(id)
        promocionInstance.delete()
    }
}

