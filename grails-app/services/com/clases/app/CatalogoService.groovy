package com.clases.app

import com.software.componente.app.Message
import com.software.componente.app.ObjectException
import grails.gorm.transactions.Transactional

@Transactional
class CatalogoService {

    /**
     * Busca y devuelve una instancia de un Producto mediante el id
     * @param id Identificador del Producto
     * @return Instancia del Producto encontrada
     */
    Catalogo get(long id) {
        log.info 'Plugin : AppPuntos, Servicio : catalogo, Metodo : get'
        return Catalogo.get(id)
    }

    /**
     * Guarda en la base de datos los campos de un Producto, nuevo o actualizado
     * @param catalogoInstance Producto a actualizar o crear
     * @return Producto actualizado o creado
     * @throws com.software.componente.app.ObjectException Al encontrar algun error en los campos*/
    Catalogo save(Catalogo catalogoInstance) throws Exception {
        log.info 'Plugin : AppPuntos, Servicio : catalogo, Metodo : save'
        try {
            if (catalogoInstance.validate() && catalogoInstance.save()) {
                log.info 'Plugin : AppPuntos, Servicio : catalogo, Metodo : save, Completado'
                return catalogoInstance
            }
            log.error 'Plugin : AppPuntos,  Servicio : catalogo, Metodo : save, Error en Guardado'
            throw new ObjectException(Message.getMensaje(codigo: 'save.fail', parametros: [
                    Message.getMensaje('catalogo.label', 'Catalogo')
            ]), catalogoInstance)
        }
        catch (org.springframework.dao.OptimisticLockingFailureException e) {
            throw new ObjectException(
                    Message.getMensaje([
                            codigo    : "default.optimistic.locking.failure",
                            parametros: [Message.getMensaje('catalogo.label', 'Catalogo')]
                    ]),
                    catalogoInstance, true
            )
        }
    }

    /**
     * Creacion de un nueva Producto
     * @param productoMap Datos necesarios para la creacion de la nuevo Producto
     * @return Instancia del Producto creado
     **/
    Catalogo create(Map catalogoMap) throws Exception {
        log.info 'Plugin : facturacionNomina, Servicio : catalogo, Metodo : create Iniciando'
        try {
            Catalogo catalogoInstance = new Catalogo()
            catalogoInstance.nombre = catalogoMap.nombre
            catalogoInstance.codigo= catalogoMap.codigo
            catalogoInstance.clasificacion= catalogoMap.clasificacion
            catalogoInstance.descripcion = catalogoMap.descripcion
            catalogoInstance.precio = catalogoMap.precio as BigDecimal
            catalogoInstance.disponibilidad= catalogoMap.disponibilidad as int
            catalogoInstance.proveedor = catalogoMap.proovedor

            if (catalogoMap.inactivo) {
                catalogoInstance.activo = false
            }

            this.save(catalogoInstance)
            log.info 'Plugin : facturacionNomina, Servicio : catalogo, Metodo : create Completado'
            return catalogoInstance
        }
        catch (Exception e) {
            log.error 'Plugin : facturacionNomina, Servicio : catalogo, Metodo : create, Error'
            throw e
        }
    }


    /**
     * Actualizacion de un Producto existente
     * @param catalogoMap Datos necesarios para la actualizacion de un Producto
     * @return Instancia del Producto actualizado
     * @throws RuntimeException Al no encontrar el Producto para actualizar
     **/
    Catalogo update(Map catalogoMap) throws Exception {
        log.info 'Plugin : appPuntos, Servicio : catalogo, Metodo : update Iniciando'
        try {
            Catalogo catalogoInstance = this.get(catalogoMap.id as long)
            if (!catalogoInstance) {
                log.error 'Plugin : appPuntos, Servicio : catalogo, Metodo : update, Error No Encontrado'
                throw new RuntimeException(Message.getMensaje(codigo: 'default.not.found.message', parametros: [
                        Message.getMensaje('catalogo.label', 'Catalogo'), catalogoInstance.id
                ]))
            }
            catalogoInstance.nombre = catalogoMap.nombre
            catalogoInstance.codigo= catalogoMap.codigo
            catalogoInstance.clasificacion= catalogoMap.clasificacion
            catalogoInstance.descripcion = catalogoMap.descripcion
            catalogoInstance.precio = catalogoMap.precio as BigDecimal
            catalogoInstance.disponibilidad= catalogoMap.disponibilidad as int
            catalogoInstance.proveedor = catalogoMap.proovedor

            this.save(catalogoInstance)
            log.info 'Plugin : appPuntos, Servicio : catalogo, Metodo : update Completado'
            return catalogoInstance
        } catch (Exception e) {
            log.error 'Plugin : appPuntos, Servicio : catalogo, Metodo : update, Error'
            throw e
        }
    }


}
