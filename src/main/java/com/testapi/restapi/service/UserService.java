package com.testapi.restapi.service;

import com.testapi.restapi.domain.Company;
import com.testapi.restapi.domain.UserResponse;
import com.testapi.restapi.domain.Users;
import com.testapi.restapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private Logger log = LoggerFactory.getLogger(ExternalUserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExternalUserService externalUserService;

    public UserResponse update(int userId, Company company) {
        Users users = userRepository.update(userId, company);
        return getUserDetails(userId, users);
    }

    public UserResponse findOne(int userId) {
        Users users = userRepository.findOne(userId);
        return getUserDetails(userId, users);
    }

    private UserResponse getUserDetails(int userId, Users users) {
        ResponseEntity<UserResponse> extResp = externalUserService.getUserDetails(userId);
        UserResponse userResponse = null;
        if (extResp.getStatusCode() == HttpStatus.OK) {
            userResponse = extResp.getBody();
            userResponse.setCompany(users.getCompany());
        } else {
            log.error("Error response {} form extenal system", extResp.getStatusCodeValue());
        }
        return userResponse;
    }
}
