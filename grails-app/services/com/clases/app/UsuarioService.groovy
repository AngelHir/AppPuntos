package com.clases.app

import com.software.componente.app.ObjectException
import com.software.componente.app.Message
import grails.gorm.transactions.Transactional

@Transactional
class UsuarioService {

    /**
     * Busca y devuelve una instancia del Usuario mediante el id
     * @param id Identificador del usuario
     * @return Instancia del Usuario encontrada
     */
    Usuario get(long id) {
        log.info 'Plugin : AppPuntos, Servicio : Usuario, Metodo : get'
        return Usuario.get(id)
    }

    /**
     * Guarda en la base de datos los campos de un Usuario, nuevo o actualizado
     * @param usuarioInstance Usuario a actualizar o crear
     * @return Usuario actualizado o creado
     * @throws ObjectException Al encontrar algun error en los campos*/
    Usuario save(Usuario usuarioInstance) throws Exception {
        log.info 'Plugin : AppPuntos, Servicio : usuario, Metodo : save'
        try {
            if (usuarioInstance.validate() && usuarioInstance.save()) {
                log.info 'Plugin : AppPuntos, Servicio : usuario, Metodo : save, Completado'
                return usuarioInstance
            }
            log.error 'Plugin : AppPuntos,  Servicio : usuario, Metodo : save, Error en Guardado'
            throw new ObjectException(Message.getMensaje(codigo: 'save.fail', parametros: [
                    Message.getMensaje('usuario.label', 'usuario')
            ]), usuarioInstance)
        }
        catch (org.springframework.dao.OptimisticLockingFailureException e) {
            throw new ObjectException(
                    Message.getMensaje([
                            codigo    : "default.optimistic.locking.failure",
                            parametros: [Message.getMensaje('usuario.label', 'usuario')]
                    ]),
                    usuarioInstance, true
            )
        }
    }

    /**
     * Creacion de un nuevo Usuario
     * @param usuarioMap Datos necesarios para la creacion del nuevo Usuario
     * @return Instancia del Usuario creado
     **/
    Usuario create(Map usuarioMap) throws Exception {
        log.info 'Plugin : facturacionNomina, Servicio : usuario, Metodo : create Iniciando'
        try {
            Usuario usuarioInstance = new Usuario()
            usuarioInstance.nombre = usuarioMap.nombre
            usuarioInstance.direccion = usuarioMap.direccion
            usuarioInstance.contrasenia=usuarioMap.contrasenia
            usuarioInstance.numeroTelefono = usuarioMap.numeroTelefono
            usuarioInstance.correoElectronico = usuarioMap.correoElectronico

            if (usuarioMap.inactivo) {
                usuarioInstance.activo = false
            }

            this.save(usuarioInstance)
            log.info 'Plugin : facturacionNomina, Servicio : usuario, Metodo : create Completado'
            return usuarioInstance
        }
        catch (Exception e) {
            log.error 'Plugin : facturacionNomina, Servicio : usuario, Metodo : create, Error'
            throw e
        }
    }


    /**
     * Actualizacion de un Banco existente
     * @param usuarioMap Datos necesarios para la actualizacion del Usuario
     * @return Instancia del Usuario actualizado
     * @throws RuntimeException Al no encontrar el Usuario para actualizar
     **/
    Usuario update(Map usuarioMap) throws Exception {
        log.info 'Plugin : appPuntos, Servicio : usuario, Metodo : update Iniciando'
        try {
            Usuario usuarioInstance = this.get(usuarioMap.id as long)
            if (!usuarioInstance) {
                log.error 'Plugin : appPuntos, Servicio : Usuario, Metodo : update, Error No Encontrado'
                throw new RuntimeException(Message.getMensaje(codigo: 'default.not.found.message', parametros: [
                        Message.getMensaje('usuario.label', 'Usuario'), usuarioInstance.id
                ]))
            }
            usuarioInstance.nombre = usuarioMap.nombre
            usuarioInstance.direccion = usuarioMap.direccion
            usuarioInstance.contrasenia=usuarioMap.contrasenia
            usuarioInstance.numeroTelefono = usuarioMap.numeroTelefono
            usuarioInstance.correoElectronico = usuarioMap.correoElectronico

            this.save(usuarioInstance)
            log.info 'Plugin : appPuntos, Servicio : Usuario, Metodo : update Completado'
            return usuarioInstance
        } catch (Exception e) {
            log.error 'Plugin : appPuntos, Servicio : Usuario, Metodo : update, Error'
            throw e
        }
    }
}
