package com.devsmile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.devsmile.service.UserService;
import com.devsmile.service.model.UserEntity;

@RestController
public class MainController { // AGE 3 //

    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Integer id) {
        return new UserService().getUserById(id);
    }
}