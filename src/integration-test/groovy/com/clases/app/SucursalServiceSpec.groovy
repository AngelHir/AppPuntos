package com.clases.app

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SucursalServiceSpec extends Specification {

    SucursalService sucursalService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Sucursal(...).save(flush: true, failOnError: true)
        //new Sucursal(...).save(flush: true, failOnError: true)
        //Sucursal sucursal = new Sucursal(...).save(flush: true, failOnError: true)
        //new Sucursal(...).save(flush: true, failOnError: true)
        //new Sucursal(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //sucursal.id
    }

    void "test get"() {
        setupData()

        expect:
        sucursalService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Sucursal> sucursalList = sucursalService.list(max: 2, offset: 2)

        then:
        sucursalList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        sucursalService.count() == 5
    }

    void "test delete"() {
        Long sucursalId = setupData()

        expect:
        sucursalService.count() == 5

        when:
        sucursalService.delete(sucursalId)
        sessionFactory.currentSession.flush()

        then:
        sucursalService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Sucursal sucursal = new Sucursal()
        sucursalService.save(sucursal)

        then:
        sucursal.id != null
    }
}
