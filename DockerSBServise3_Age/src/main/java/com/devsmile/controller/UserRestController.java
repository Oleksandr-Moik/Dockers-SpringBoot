package com.devsmile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsmile.model.UserDTO;
import com.devsmile.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping({ "/user/*", "/u" })
public class UserRestController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getUsers() {
		log.info("3 Call getUsers");
		return ResponseEntity.ok(userService.getUsers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("id") Integer id) {
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