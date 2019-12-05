package com.devsmile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devsmile.model.UserDTO;
import com.devsmile.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserRestController { 
	
	@Autowired
	private UserService userService;
	
//	@GetMapping("/user")
	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<UserDTO>> getUsersList(){
		log.info("2 Call getUsersList");
		List<UserDTO> users = userService.getUsersList();
		log.info("2 Reterned list = {}",users);
		if(!users.equals(null)) {
			return ResponseEntity.ok(users);
		}
		else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
    //@GetMapping("/user/{id}", produces = "application/json")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id){
    	
		log.info("2 Call getUserById with param id = {}", id);

		UserDTO userDTO = userService.getUserById(id);
		log.info("2 Returnet userDTO entity = {}", userDTO.toString());

		if (!userDTO.equals(null)) {
			return ResponseEntity.ok().body(userDTO);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
    }
}