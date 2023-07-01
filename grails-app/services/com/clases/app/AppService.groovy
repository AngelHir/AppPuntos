package com.clases.app

import grails.gorm.transactions.Transactional

@Transactional
class AppService {

    def init() {
        def usuario1 = crearUsuario("Usuario 1", "usuario1@example.com")
        def usuario2 = crearUsuario("Usuario 2", "usuario2@example.com")

        def integracion1 = crearIntegracion("Integración 1", "Descripción 1")
        def integracion2 = crearIntegracion("Integración 2", "Descripción 2")

        def notificacion1 = crearNotificacion("Notificación 1", "Contenido 1")
        def notificacion2 = crearNotificacion("Notificación 2", "Contenido 2")

        def promocion1 = crearPromocion("Promoción 1", "Descripción 1", 10)
        def promocion2 = crearPromocion("Promoción 2", "Descripción 2", 20)

        def soporte1 = crearSoporteCliente("Soporte 1", "Consulta 1")
        def soporte2 = crearSoporteCliente("Soporte 2", "Consulta 2")

        println("Usuario 1: ${usuario1.nombre}, Email: ${usuario1.email}")
        println("Usuario 2: ${usuario2.nombre}, Email: ${usuario2.email}")

        println("Integración 1: ${integracion1.nombre}, Descripción: ${integracion1.descripcion}")
        println("Integración 2: ${integracion2.nombre}, Descripción: ${integracion2.descripcion}")

        println("Notificación 1: ${notificacion1.titulo}, Contenido: ${notificacion1.contenido}")
        println("Notificación 2: ${notificacion2.titulo}, Contenido: ${notificacion2.contenido}")

        println("Promoción 1: ${promocion1.nombre}, Descripción: ${promocion1.descripcion}, Descuento: ${promocion1.descuento}")
        println("Promoción 2: ${promocion2.nombre}, Descripción: ${promocion2.descripcion}, Descuento: ${promocion2.descuento}")

        println("Soporte 1: ${soporte1.nombre}, Consulta: ${soporte1.consulta}")
        println("Soporte 2: ${soporte2.nombre}, Consulta: ${soporte2.consulta}")
    }

    private Usuario crearUsuario(String nombre, String email) {
        def usuario = new Usuario(nombre: nombre, email: email)
        usuario.save()
        return usuario
    }

    private Integracion crearIntegracion(String nombre, String descripcion) {
        def integracion = new Integracion(nombre: nombre, descripcion: descripcion)
        integracion.save()
        return integracion
    }

    private Notificacion crearNotificacion(String titulo, String contenido) {
        def notificacion = new Notificacion(titulo: titulo, contenido: contenido)
        notificacion.save()
        return notificacion
    }

    private Promocion crearPromocion(String nombre, String descripcion, int descuento) {
        def promocion = new Promocion(nombre: nombre, descripcion: descripcion, descuento: descuento)
        promocion.save()
        return promocion
    }

    private SoporteCliente crearSoporteCliente(String nombre, String consulta) {
        def soporte = new SoporteCliente(nombre: nombre, consulta: consulta)
        soporte.save()
        return soporte
    }
}
