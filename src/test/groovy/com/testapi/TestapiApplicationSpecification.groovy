package com.testapi

import com.testapi.restapi.config.IntegrationTestConfiguration
import com.testapi.restapi.domain.*
import com.testapi.restapi.repository.UserRepository
import com.testapi.restapi.service.ExternalUserService
import org.springframework.aop.framework.Advised
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.data.cassandra.core.CassandraOperations
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(IntegrationTestConfiguration)
class TestapiApplicationSpecification extends Specification {

    final def USER_API_URL = '/testapi/v1/user/1'
    final def USER_ID = 1
    final def COMPANY_BS = 'bs'
    final def COMPANY_CATCHP = 'catch'
    final def COMPANY_NAME = 'name'
    final def USER_NAME = 'username'
    final def EMAIL = 'mail'
    final def PHONE = '1654651'
    final def SITE = 'site'
    final def STREET = 'street'
    final def CITY = 'city'
    final def ZIP_CODE = '12313'
    final def SUITE = 'suit'
    final def GEO_LAN = '152.236.45'
    final def GEO_LNG = '152.236.78'
    Company company

    @Autowired
    TestRestTemplate restTemplate

    @Autowired
    UserRepository userRepository

    @Autowired
    ExternalUserService externalUserService

    @Autowired
    CassandraOperations cassandraTemplate

    void setup() {
        company = new Company(name: COMPANY_NAME, catchPhrase: COMPANY_CATCHP, bs: COMPANY_BS)
    }

    def 'Get call should return user data with company details - data present'() {
        given:
        def userRepository = getTargetObject(userRepository)

        when:
        def user_details = restTemplate.getForEntity(USER_API_URL, UserResponse)

        then:
        user_details.statusCode == HttpStatus.OK
        verifyResponse(user_details.body)

        1 * userRepository.findOne(USER_ID) >> new Users(USER_ID, company)
        1 * externalUserService.getUserDetails(USER_ID) >> new ResponseEntity<UserResponse>(getResponse(), HttpStatus.OK)

    }

    def 'Put call should update existing user  and return user data with company details - data present'() {
        given:
        def userRepository = getTargetObject(userRepository)
        HttpEntity<Company> request = new HttpEntity<>(company);

        when:
        def user_details = restTemplate.exchange(USER_API_URL, HttpMethod.PUT, request, UserResponse)

        then:
        user_details.statusCode == HttpStatus.ACCEPTED
        verifyResponse(user_details.body)

        1 * userRepository.update(*_) >> new Users(USER_ID, company)
        1 * externalUserService.getUserDetails(USER_ID) >> new ResponseEntity<UserResponse>(getResponse(), HttpStatus.OK)

    }

    static <T> T getTargetObject(Object proxy) throws Exception {
        if (AopUtils.isAopProxy(proxy)) {
            return (T) ((Advised) proxy).getTargetSource().getTarget();
        } else {
            return (T) proxy;
        }
    }

    def getResponse() {
        Geo geo = new Geo(lat: GEO_LAN, lng: GEO_LNG)
        Address address = new Address(street: STREET, city: CITY, geo: geo, zipcode: ZIP_CODE, suite: SUITE)
        Company company = new Company(name: COMPANY_NAME, bs: COMPANY_BS, catchPhrase: COMPANY_CATCHP)
        new UserResponse(id: 1, name: USER_NAME, email: EMAIL, address: address, phone: PHONE, website: SITE, company: company)
    }

    def verifyResponse(response) {
        response.id == USER_ID
        response.company.name == COMPANY_NAME
        response.company.catchPhrase == COMPANY_CATCHP
        response.company.bs == COMPANY_BS
        response.name == USER_NAME
        response.email == EMAIL
        response.address.suite == SUITE
        response.address.street == STREET
        response.address.city == CITY
        response.address.zipcode == ZIP_CODE
        response.address.geo.lng == GEO_LNG
        response.address.geo.lat == GEO_LAN

    }
}
