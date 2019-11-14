package com.devsmile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devsmile.model.User;
import com.devsmile.model.UserDTO;
import com.devsmile.service.UserService;

@RestController
public class UserRestController { 
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
	public ResponseEntity<List<User>> getUsers(){
		return userService.getUsers();
	}
	
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }
    
    @PostMapping("/user/")
    public ResponseEntity<User> newUser(@RequestBody User user){
    	return userService.newUser(user);
    }
    
    @PutMapping("/user/{id}")
    public ResponseEntity<User> editUser(@RequestBody User user, @PathVariable("id") Integer id){
    	return userService.editUser(user, id);
    }
    
    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> removeUser(@PathVariable("id") Integer id){
    	return userService.removeUser(id);
    }
}