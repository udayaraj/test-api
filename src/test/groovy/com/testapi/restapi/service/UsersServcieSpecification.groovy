package com.testapi.restapi.service

import com.testapi.restapi.domain.Address
import com.testapi.restapi.domain.Company
import com.testapi.restapi.domain.Geo
import com.testapi.restapi.domain.UserResponse
import com.testapi.restapi.domain.Users
import com.testapi.restapi.repository.UserRepository
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Shared
import spock.lang.Specification

class UsersServcieSpecification extends Specification {

    UserRepository mockUserRepository = Mock()
    ExternalUserService mockExternalUserService = Mock()
    Logger logMock = Mock()
    UserService userService
    @Shared
    Company company

    void setup() {
        userService = new UserService(userRepository: mockUserRepository, externalUserService: mockExternalUserService, log: logMock)
        company = new Company(name: 'name', bs: 'bs', catchPhrase: 'catch')
    }

    def 'update - Updates company details'() {
        given:

        Users user = new Users(1321, company)

        when:

        UserResponse userResponse = userService.update(1234, company)

        then:
        userResponse.company == company

        1 * mockExternalUserService.getUserDetails(*_) >> new ResponseEntity<UserResponse>(getResponse(), HttpStatus.OK)
        1 * mockUserRepository.update(*_) >> user
    }

    def 'findOne - Returns the existing User details for given userId'() {

        when:

        UserResponse userResponse = userService.findOne(1234)

        then:
        userResponse == userResponse

        1 * mockExternalUserService.getUserDetails(*_) >> new ResponseEntity<UserResponse>(getResponse(), HttpStatus.OK)

        1 * mockUserRepository.findOne(*_) >> new Users(id: 1231, company: company)

    }


    def 'findOne - Returns the existing User details for given userId - not present 400 resp'() {

        when:

        userService.findOne(1234)

        then:

        1 * logMock.error('Error response {} form extenal system', 404)

        1 * mockExternalUserService.getUserDetails(*_) >> new ResponseEntity<UserResponse>(new UserResponse(), HttpStatus.NOT_FOUND)

        1 * mockUserRepository.findOne(*_) >> new Users(id: 1231, company: company)

    }

    def getResponse() {
        Geo geo = new Geo(lat: '152.236.45', lng: '10.2.3')
        Address address = new Address(street: 'street', city: 'city', geo: geo, zipcode: '12313', suite: 'suit')
        Company company = new Company(name: 'name', bs: 'bs', catchPhrase: 'catchphrase')
        new UserResponse(id: 1234, name: 'username', email: 'mail', address: address, phone: '1654651', website: 'site', company: company)
    }
}
