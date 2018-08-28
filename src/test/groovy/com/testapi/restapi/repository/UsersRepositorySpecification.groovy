package com.testapi.restapi.repository

import com.testapi.restapi.domain.Company
import com.testapi.restapi.domain.Users
import com.testapi.restapi.repository.UserRepository
import org.springframework.data.cassandra.core.CassandraOperations
import spock.lang.Specification

class UsersRepositorySpecification extends Specification {

    CassandraOperations mockCassandraOperations = Mock()
    UserRepository userRepository

    void setup() {
        userRepository = new UserRepository(cassandraTemplate: mockCassandraOperations)
    }

    def "Update - Update the existing user in cassandra by id"() {

        given:

        Company company = new Company(name: 'name', bs: 'bs', catchPhrase: 'catch')
        when:

        userRepository.update(1234, company)

        then:

        2 * mockCassandraOperations.selectOneById(*_) >> new Users()
        1 * mockCassandraOperations.update(*_)
    }

    def "FindOne - Find the user by id"() {

        when:

        userRepository.findOne(1234)

        then:

        1 * mockCassandraOperations.selectOneById(*_)
    }
}
