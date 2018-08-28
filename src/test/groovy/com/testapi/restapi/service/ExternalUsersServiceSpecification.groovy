package com.testapi.restapi.service

import com.testapi.restapi.domain.Address
import com.testapi.restapi.domain.Company
import com.testapi.restapi.domain.Geo
import com.testapi.restapi.domain.UserResponse
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class ExternalUsersServiceSpecification extends Specification {

    ExternalUserService externalUserService
    RestTemplate mockRestTemplate = Mock()
    Logger logMock = Mock()

    void setup() {
        externalUserService = new ExternalUserService(restTemplate: mockRestTemplate, log: logMock)
    }

    def 'getUserDetails - validate external user api response'() {

        when:
        def response = externalUserService.getUserDetails(1234)
        then:

        response.statusCode.is2xxSuccessful()
        1 * mockRestTemplate.getForEntity(*_) >> new ResponseEntity<UserResponse>(getResponse(), HttpStatus.OK)

    }


    def 'getUserDetails - 404 response from external api'() {

        when:
        def response = externalUserService.getUserDetails(1234)
        then:

        response.statusCode.is4xxClientError()

        1 * mockRestTemplate.getForEntity(*_) >> new ResponseEntity<String>(new UserResponse(), HttpStatus.NOT_FOUND)

    }

    def 'handle HttpClientErrorException'() {

        when:
        externalUserService.getUserDetails(1234)

        then:
        1 * mockRestTemplate.getForEntity(*_) >> { throw new HttpClientErrorException(HttpStatus.BAD_GATEWAY) }
        1 * logMock.error("Exception occurred while making external get call", '502 BAD_GATEWAY')

    }

    def 'throw if exception is other than HttpClientErrorException'() {

        when:
        externalUserService.getUserDetails(1234)
        then:
        1 * mockRestTemplate.getForEntity(*_) >> { throw new Exception() }
        thrown(Exception)
    }

    def getResponse() {
        Geo geo = new Geo(lat: '152.236.45', lng: '10.2.3')
        Address address = new Address(street: 'street', city: 'city', geo: geo, zipcode: '12313', suite: 'suit')
        Company company = new Company(name: 'name', bs: 'bs', catchPhrase: 'catchphrase')
        new UserResponse(id: 1234, name: 'username', email: 'mail', address: address, phone: '1654651', website: 'site', company: company)
    }
}
