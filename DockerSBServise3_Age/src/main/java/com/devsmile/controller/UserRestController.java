package com.devsmile.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devsmile.model.UserDTO;
import com.devsmile.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserRestController {

	private final UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<UserDTO>> getUsersList() {
		log.info("[Age]-[UserRestController] Call getUsersList");

		List<UserDTO> usersDTO = userService.getUsersList();
		log.info("[Age]-[UserRestController]  = {}", usersDTO);

		return ResponseEntity.ok(userService.getUsersList());
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) {
		log.info("[Age]-[UserRestController] Call getUserById with param id = {}", id);

		UserDTO userDTO = userService.getUserById(id);
		log.info("[Age]-[UserRestController] Returnet userDTO entity = {}", userDTO.toString());

		return ResponseEntity.ok().body(userDTO);
	}
}