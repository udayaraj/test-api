package com.testapi.restapi.repository;

import com.testapi.restapi.domain.Company;
import com.testapi.restapi.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private CassandraOperations cassandraTemplate;

    public Users update(int userId, Company company) {
        if (findOne(userId) != null)
            cassandraTemplate.update(new Users(userId, company));
        return findOne(userId);
    }

    public Users findOne(int userId) {
        return cassandraTemplate.selectOneById(userId, Users.class);
    }

}
