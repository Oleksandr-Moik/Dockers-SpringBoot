package com.devsmile.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsmile.model.User;
import com.devsmile.model.UserDTO;
import com.devsmile.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public List<UserDTO> getUsersList() {
		List<User> users = userRepository.findAll();
		log.info("[Age]-[UserService] Result users = {}", users.toString());

		List<UserDTO> usersDTO = users.stream().map(user -> UserTransformer.convertToUserDTO(user))
				.collect(Collectors.toList());
		log.info("[Age]-[UserService] Result users = {}", users.toString());

		return usersDTO;
	}

	public UserDTO getUserById(Integer id) {
		User user = userRepository.findById(id).get();
		log.info("[Age]-[UserService] Result Optional<User> = {}", user.toString());

		UserDTO userDTO = UserTransformer.convertToUserDTO(user);
		log.info("[Age]-[UserService] UserDTO: {}", userDTO);
		return userDTO;
	}
}
