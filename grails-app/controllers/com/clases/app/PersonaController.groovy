package com.clases.app

import com.software.componente.app.ObjectException
import com.software.componente.app.Message
import grails.converters.JSON

class PersonaController {

    PersonaService personaService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /**
     * Controlador para la creacion de un nuevo Usuario
     * @return Mapa con mensaje de exito de creacion
     * */
    def save() {
        log.info 'Plugin : AppPuntos, Controlador : Persona, Accion : save'
        try {
            Persona personaInstance = personaService.create(JSON.parse(request) as Map)
            render(contentType: "application/json") {
                success(
                        Message.getMensaje(codigo: 'default.created.message', parametros: [
                                Message.getMensaje('persona.label', 'Persona'),
                                personaInstance.id
                        ])
                )
            }
        } catch (ObjectException e) {
            render(status: 404, e.responseObject as JSON, contentType: "application/json")
        } catch (Exception e) {
            render(status: 404, contentType: "application/json") { mensajeError(e.getMessage()) }
        }
    }


}
