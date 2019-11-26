package com.devsmile.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.devsmile.model.UserDTO;
import com.devsmile.repository.UserRepository;
import com.devsmile.service.model.UserEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserRestController { // LastName 2 //

	@Value( "${jdbc.url}" )
	private static String AGE_SERVICE_URL; 
	
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) throws Exception{
        if (userRepository.findById(id).isPresent()) {
            
            HttpHeaders httpHeaders= new HttpHeaders();
            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> httpEntity= new HttpEntity<String>("parameters", httpHeaders);
            RestTemplate restTemplate = new RestTemplate();
            
            ResponseEntity<User> response = restTemplate.exchange(String.format(AGE_SERVICE_URL+"/{}", id), HttpMethod.GET, httpEntity, User.class);
            
            User user = response.getBody().builder().lastName(userRepository.findById(id).get().getLastName()).build();
            
            log.info("Service GET 2 lastName: {}",user.toString());
            
            return ResponseEntity.ok(user);
        } else {
            log.error("Service GET 2 lastName: user with id={} not found.",id);
            return ResponseEntity.badRequest().body(null);
        }
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