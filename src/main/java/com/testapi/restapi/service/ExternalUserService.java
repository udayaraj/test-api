package com.testapi.restapi.service;

import com.testapi.restapi.domain.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalUserService {
    private static final String URL = "https://jsonplaceholder.typicode.com/users/";
    private Logger log = LoggerFactory.getLogger(ExternalUserService.class);

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<UserResponse> getUserDetails(int userId) {
        ResponseEntity<UserResponse> userResponse = null;
        String url = URL + userId;
        try {
            userResponse = restTemplate.getForEntity(url, UserResponse.class);
        } catch (HttpClientErrorException exception) {
            log.error("Exception occurred while making external get call", exception.getMessage());
        }
        return userResponse;
    }

}
