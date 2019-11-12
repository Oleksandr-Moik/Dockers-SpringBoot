package com.devsmile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devsmile.repository.dao.UserRepository;
import com.devsmile.service.model.UserEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MainController { // AGE 3 //

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Integer id) {
        if (userRepository.findById(id).isPresent()) {
            Integer age; //userRepository.findById(id).get().getAge();
            //UserEntity user = new UserEntity(id, "", "", age);
//            log.info("Service GET 3 age: {}",user.toString());
            return ResponseEntity.ok().body(null);//user);
        } else {
            log.error("Service GET 3 age: user with id={} not found.",id);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/user/", method = RequestMethod.POST, produces = { "text/plain", "application/json" })
    public ResponseEntity<UserEntity> insertUser(@RequestBody UserEntity user) {
        UserEntity newUser = null;//userRepository.save(user);
        
        log.info("Service POST 3 age: {}",newUser.toString());
        
        return ResponseEntity.ok(newUser);
    }
}