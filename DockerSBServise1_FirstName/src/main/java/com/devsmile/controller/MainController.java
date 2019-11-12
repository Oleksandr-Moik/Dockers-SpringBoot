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
import org.springframework.web.client.RestTemplate;

import com.devsmile.dao.UserRepository;
import com.devsmile.model.UserEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MainController { // FirstName 1 //

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Integer id) {
        if (userRepository.findById(id).isPresent()) {
        
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UserEntity> response = restTemplate.getForEntity("http://LastName:8082/user/"+id, UserEntity.class);
            
            UserEntity user = response.getBody();
            user.setFirstName(userRepository.findById(id).get().getFirstName());
            
            log.info("Service GET 1 FirstName: {}",user.toString());
            
            return ResponseEntity.ok(user);
        } else {
            log.error("Service GET 1 FirstName: user with id={} not found.",id);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = { "text/plain", "application/json" })
    public ResponseEntity<UserEntity> insertUser(@RequestBody UserEntity user) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserEntity> response = restTemplate.postForEntity("http://LastName:8080/user/", user, UserEntity.class);
        
        UserEntity newUser = response.getBody();
        newUser.setFirstName(user.getFirstName());
        
        log.info("Service POST 1 FirstName: {}",newUser.toString());

        userRepository.saveAndFlush(newUser);
        
        return ResponseEntity.ok(newUser);
    }
}