package com.clases.app

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SesionServiceSpec extends Specification {

    SesionService sesionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Sesion(...).save(flush: true, failOnError: true)
        //new Sesion(...).save(flush: true, failOnError: true)
        //Sesion sesion = new Sesion(...).save(flush: true, failOnError: true)
        //new Sesion(...).save(flush: true, failOnError: true)
        //new Sesion(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //sesion.id
    }

    void "test get"() {
        setupData()

        expect:
        sesionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Sesion> sesionList = sesionService.list(max: 2, offset: 2)

        then:
        sesionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        sesionService.count() == 5
    }

    void "test delete"() {
        Long sesionId = setupData()

        expect:
        sesionService.count() == 5

        when:
        sesionService.delete(sesionId)
        sessionFactory.currentSession.flush()

        then:
        sesionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Sesion sesion = new Sesion()
        sesionService.save(sesion)

        then:
        sesion.id != null
    }
}
