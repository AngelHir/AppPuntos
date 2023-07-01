package com.clases.app

import grails.gorm.transactions.Transactional

@Transactional
class NotificacionService {
    def createNotificacion(params) {
        def notificacionInstance = new Notificacion(params)
        if (notificacionInstance.save()) {
            return notificacionInstance
        } else {
            throw new RuntimeException("Error al crear la notificación")
        }
    }

    def getNotificacion(id) {
        return Notificacion.get(id)
    }

    def updateNotificacion(id, params) {
        def notificacionInstance = Notificacion.get(id)
        notificacionInstance.properties = params
        if (notificacionInstance.save()) {
            return notificacionInstance
        } else {
            throw new RuntimeException("Error al actualizar la notificación")
        }
    }

    def deleteNotificacion(id) {
        def notificacionInstance = Notificacion.get(id)
        notificacionInstance.delete()
    }
}


