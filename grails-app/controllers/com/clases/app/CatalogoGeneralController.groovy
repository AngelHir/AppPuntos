package com.clases.app


import grails.rest.*
import grails.converters.*

class CatalogoGeneralController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
}
