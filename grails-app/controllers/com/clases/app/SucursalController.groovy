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
class SucursalController {

    SucursalService sucursalService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond sucursalService.list(params), model:[sucursalCount: sucursalService.count()]
    }

    def show(Long id) {
        respond sucursalService.get(id)
    }

    @Transactional
    def save(Sucursal sucursal) {
        if (sucursal == null) {
            render status: NOT_FOUND
            return
        }
        if (sucursal.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sucursal.errors
            return
        }

        try {
            sucursalService.save(sucursal)
        } catch (ValidationException e) {
            respond sucursal.errors
            return
        }

        respond sucursal, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Sucursal sucursal) {
        if (sucursal == null) {
            render status: NOT_FOUND
            return
        }
        if (sucursal.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sucursal.errors
            return
        }

        try {
            sucursalService.save(sucursal)
        } catch (ValidationException e) {
            respond sucursal.errors
            return
        }

        respond sucursal, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        sucursalService.delete(id)

        render status: NO_CONTENT
    }
}
