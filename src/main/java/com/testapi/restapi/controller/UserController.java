package com.testapi.restapi.controller;

import com.testapi.restapi.domain.Company;
import com.testapi.restapi.domain.UserResponse;
import com.testapi.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("testapi/v1/")
public class UserController {
    @Autowired
    UserService userService;

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable("id") int userId, @RequestBody Company company) {
        UserResponse userResponse = userService.update(userId, company);
        if (null != userResponse) {
            return new ResponseEntity<>(userResponse, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Invalid user id " + userId, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int userId) {
        UserResponse userResponse = userService.findOne(userId);
        if (null != userResponse) {
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid user id " + userId, HttpStatus.NOT_FOUND);
        }
    }


}
