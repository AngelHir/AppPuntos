package com.clases.app

import grails.gorm.transactions.Transactional

@Transactional
class PuntosService {

    def createPuntos(params) {
        def puntosInstance = new Puntos(params)
        if (puntosInstance.save()) {
            return puntosInstance
        } else {
            throw new RuntimeException("Error al crear los puntos")
        }
    }

    def getPuntos(id) {
        return Puntos.get(id)
    }

    def updatePuntos(id, params) {
        def puntosInstance = Puntos.get(id)
        puntosInstance.properties = params
        if (puntosInstance.save()) {
            return puntosInstance
        } else {
            throw new RuntimeException("Error al actualizar los puntos")
        }
    }

    def deletePuntos(id) {
        def puntosInstance = Puntos.get(id)
        puntosInstance.delete()
    }
}
