package com.clases.app

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class SesionController {

    SesionService sesionService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond sesionService.list(params), model:[sesionCount: sesionService.count()]
    }

    def show(Long id) {
        respond sesionService.get(id)
    }

    @Transactional
    def save(Sesion sesion) {
        if (sesion == null) {
            render status: NOT_FOUND
            return
        }
        if (sesion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sesion.errors
            return
        }

        try {
            sesionService.save(sesion)
        } catch (ValidationException e) {
            respond sesion.errors
            return
        }

        respond sesion, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Sesion sesion) {
        if (sesion == null) {
            render status: NOT_FOUND
            return
        }
        if (sesion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sesion.errors
            return
        }

        try {
            sesionService.save(sesion)
        } catch (ValidationException e) {
            respond sesion.errors
            return
        }

        respond sesion, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        sesionService.delete(id)

        render status: NO_CONTENT
    }
}
