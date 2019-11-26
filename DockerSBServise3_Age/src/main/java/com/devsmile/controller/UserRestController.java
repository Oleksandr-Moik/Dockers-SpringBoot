package com.devsmile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.devsmile.model.UserDTO;
import com.devsmile.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

	@GetMapping("/user")
	public ResponseEntity<List<UserDTO>> getUsersList() {
		log.info("3 Call getUsers");
		return ResponseEntity.ok(userService.getUsersList());
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) {
		log.info("3 Call getUser with param id = {}", id);

		UserDTO userDTO = userService.getUser(id);
		log.info("3 Returnet userDTO entity = {}", userDTO.toString());

		if (userDTO.equals(null)) {
			return ResponseEntity.badRequest().body(null);
		} else {
			return ResponseEntity.ok().body(userDTO);
		}
	}
}