package com.clases.app


import grails.rest.*
import grails.converters.*

class CashbackController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
}
