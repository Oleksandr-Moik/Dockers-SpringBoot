package com.devsmile.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.devsmile.domain.dto.UserDTO;
import com.devsmile.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/user")
	public ResponseEntity<List<UserDTO>> getUsersList() {
		log.info("[Age]-[UserRestController] Call getUsersList");

		List<UserDTO> usersDTO = userService.getUsersList();
		log.info("[Age]-[UserRestController]  = {}", usersDTO);

		return ResponseEntity.ok(userService.getUsersList());
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) {
		log.info("[Age]-[UserRestController] Call getUserById with param id = {}", id);

		UserDTO userDTO = userService.getUserById(id);
		log.info("[Age]-[UserRestController] Returnet userDTO entity = {}", userDTO.toString());

		return ResponseEntity.ok().body(userDTO);
	}
}