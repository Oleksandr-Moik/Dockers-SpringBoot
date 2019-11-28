package com.devsmile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.devsmile.model.User;
import com.devsmile.model.UserDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserRestController { 

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) throws Exception{
    	return getUserById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = { "text/plain", "application/json" })
    public ResponseEntity<User> insertUser(@RequestBody User user) {
                
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> response = restTemplate.postForEntity("http://Age:8080/user/", user, User.class);
        
        User newUser = response.getBody();
        //newUser.setLastName(user.getLastName());
        
        log.info("Service POST 2 lastName: {}",newUser.toString());
        
        //userRepository.save(newUser);
        
        return ResponseEntity.ok(newUser);
    }
}