package com.devsmile.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsmile.domain.User;
import com.devsmile.domain.dto.UserDTO;
import com.devsmile.repository.UserRepos;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepos userRepos;

	public List<UserDTO> getUsersList() {
		List<User> users = userRepos.findAll();
		log.info("[Age]-[UserService] Result users = {}", users.toString());

		List<UserDTO> usersDTO = users.stream().map(user -> UserTransformer.convertToUserDTO(user))
				.collect(Collectors.toList());
		log.info("[Age]-[UserService] Result users = {}", users.toString());

		return usersDTO;
	}

	public UserDTO getUserById(Integer id) {
		User user = userRepos.findById(id).get();
		log.info("[Age]-[UserService] Result Optional<User> = {}", user.toString());

		UserDTO userDTO = UserTransformer.convertToUserDTO(user);
		log.info("[Age]-[UserService] UserDTO: {}", userDTO);
		return userDTO;
	}
}
