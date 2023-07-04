package com.clases.app

import grails.gorm.transactions.Transactional

@Transactional
class UsuarioService {
    def createUsuario(params) {
        def usuarioInstance = new Usuario(params)
        if (usuarioInstance.save()) {
            return usuarioInstance
        } else {
            throw new RuntimeException("Error al crear el usuario")
        }
    }

    /**
     * Busca y devuelve una instancia del Banco mediante el id
     * @param id Identificador del Banco
     * @return Instancia del Banco encontrada
     */
    Usuario get(long id) {
        log.info 'Plugin : AppPuntos, Servicio : Usuario, Metodo : get'
        return Usuario.get(id)
    }

    def updateUsuario(id, params) {
        def usuarioInstance = Usuario.get(id)
        usuarioInstance.properties = params
        if (usuarioInstance.save()) {
            return usuarioInstance
        } else {
            throw new RuntimeException("Error al actualizar el usuario")
        }
    }

    def deleteUsuario(id) {
        def usuarioInstance = Usuario.get(id)
        usuarioInstance.delete()
    }
}
