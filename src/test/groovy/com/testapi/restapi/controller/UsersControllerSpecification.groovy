package com.testapi.restapi.controller

import com.testapi.restapi.controller.UserController
import com.testapi.restapi.domain.Address
import com.testapi.restapi.domain.Company
import com.testapi.restapi.domain.Geo
import com.testapi.restapi.domain.UserResponse
import com.testapi.restapi.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Shared
import spock.lang.Specification

class UsersControllerSpecification extends Specification {

    UserService mockUserService = Mock()
    UserController userController

    @Shared
    Company company
    final def USER_ID = 1234
    final def COMPANY_NAME = 'name'
    final def COMPANY_CATCHP = 'catchPhrase'
    final def COMPANY_BS = 'bs'

    void setup() {
        userController = new UserController(userService: mockUserService)
        company = new Company(catchPhrase: COMPANY_CATCHP, name: COMPANY_BS, bs: COMPANY_BS)
    }

    def "getUserByID - fetch user details by id"() {

        when:

        ResponseEntity<UserResponse> user = userController.getUserById(USER_ID)

        then:

        user.statusCode == responseCode
        if (!user) {
            user.body.company.name == COMPANY_NAME
            user.body.company.catchPhrase == COMPANY_CATCHP
            user.body.company.bs == COMPANY_BS
            user.body.id == USER_ID
        }

        1 * mockUserService.findOne(_) >> queriedUser

        where:
        scenario                                  | queriedUser   | responseCode
        'when provided user id present in db'     | getResponse() | HttpStatus.OK
        'when provided user id not present in db' | null          | HttpStatus.NOT_FOUND

    }

    def "updateUserById - update User details by id"() {

        when:

        ResponseEntity<UserResponse> userResponse = userController.updateUserById(USER_ID, company)

        then:

        userResponse.statusCode == responseCode
        if (!userResponse) {
            userResponse.body.company.name == COMPANY_NAME
            userResponse.body.company.bs == COMPANY_BS
            userResponse.body.company.catchPhrase == COMPANY_CATCHP
            userResponse.body.id == USER_ID
        }

        1 * mockUserService.update(*_) >> queriedUser

        where:
        scenario                                 | queriedUser   | responseCode
        'when provided userid present in db'     | getResponse() | HttpStatus.ACCEPTED
        'when provided userid not present in db' | null          | HttpStatus.NOT_FOUND

    }

    def getResponse() {
        Geo geo = new Geo(lat: '152.236.45', lng: '10.2.3')
        Address address = new Address(street: 'street', city: 'city', geo: geo, zipcode: '12313', suite: 'suit')
        Company company = new Company(name: COMPANY_NAME, bs: COMPANY_BS, catchPhrase: COMPANY_CATCHP)
        new UserResponse(id: 1234, name: 'username', email: 'mail', address: address, phone: '1654651', website: 'site', company: company)
    }

}
