package com.clases.app

import com.software.componente.app.Message
import com.software.componente.app.ObjectException
import grails.gorm.transactions.Transactional

@Transactional
class ProductoService {

    /**
     * Busca y devuelve una instancia de un Producto mediante el id
     * @param id Identificador del Producto
     * @return Instancia del Producto encontrado

    Producto get(long id) {
        log.info 'Plugin : appPuntos, Servicio : Producto, Metodo : get'
        Producto.get(id)
    }


     * Guarda en la base de datos los campos de un Producto nuevo o actualizado
     * @param productoInstance Producto a actualizar o crear
     * @return Producto actualizado o creado
     * @throws com.software.componente.app.ObjectException Al encontrar algun error en los campos
    Producto save(Producto productoInstance) throws Exception {
        log.info 'Plugin : appPuntos, Servicio : Producto, Metodo : save'
        try {
            if (productoInstance.validate() && productoInstance.save()) {
                log.info 'Plugin : appPuntos, Servicio : Producto, Metodo : save, Completado'
                return productoInstance
            }
            log.error 'Plugin : appPuntos,  Servicio : Producto, Metodo : save, Error en Guardado'
            throw new ObjectException(Message.getMensaje(codigo: 'save.fail', parametros: [
                    Message.getMensaje('producto.label', 'producto')
            ]), productoInstance)
        }
        catch (org.springframework.dao.OptimisticLockingFailureException e) {
            throw new ObjectException(
                    Message.getMensaje([
                            codigo    : "default.optimistic.locking.failure",
                            parametros: [Message.getMensaje('producto.label', 'producto')]
                    ]),
                    productoInstance, true
            )
        }
    }


    /**
     * Creacion de un nuevo Producto
     * @param productoMap Datos necesarios para la creacion de un nuevo Producto
     * @return Instancia del Producto creado

    Producto create(Map productoMap) throws Exception {
        log.info 'Plugin : appPuntos, Servicio : Producto, Metodo : create Iniciando'
        try {
            Producto productoInstance = new Producto()
            productoInstance.nombre = productoMap.nombre
            productoInstance.descripcion = productoMap.descripcion
            productoInstance.codigoBarra = productoMap.codigoBarra
            productoInstance.precio= productoMap.puntosRequeridos as int
            productoInstance.stock = productoMap.stock as Integer

            if (productoMap.inactivo) {
                productoInstance.activo = false
            }

            this.save(productoInstance)
            log.info 'Plugin : facturacionNomina, Servicio : Producto, Metodo : create Completado'
            return productoInstance
        }
        catch (Exception e) {
            log.error 'Plugin : facturacionNomina, Servicio : Producto, Metodo : create, Error'
            throw e
        }
    }


    /**
     * Actualizacion de un Producto existente
     * @param productoMap Datos necesarios para la actualizacion del Producto
     * @return Instancia del Producto actualizado
     * @throws RuntimeException Al no encontrar el Producto para actualizar

    Producto update(Map productoMap) throws Exception {
        log.info 'Plugin : appPuntos, Servicio : Producto, Metodo : update Iniciando'
        try {
            Producto productoInstance = this.get(productoMap.id as long)
            if (!productoInstance) {
                log.error 'Plugin : appPuntos, Servicio : Producto, Metodo : update, Error No Encontrado'
                throw new RuntimeException(Message.getMensaje(codigo: 'default.not.found.message', parametros: [
                        Message.getMensaje('producto.label', 'Producto'), productoInstance.id
                ]))
            }
            productoInstance.nombre = productoMap.nombre
            productoInstance.descripcion = productoMap.descripcion
            productoInstance.codigoBarra = productoMap.codigoBarra
            productoInstance.precio= productoMap.puntosRequeridos as int
            productoInstance.stock = productoMap.stock as Integer

            this.save(productoInstance)
            log.info 'Plugin : appPuntos, Servicio : Producto, Metodo : update Completado'
            return productoInstance
        } catch (Exception e) {
            log.error 'Plugin : appPuntos, Servicio : Producto, Metodo : update, Error'
            throw e
        }
    }
    **/
}

