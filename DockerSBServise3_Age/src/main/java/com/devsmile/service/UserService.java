package com.devsmile.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsmile.model.User;
import com.devsmile.model.UserDTO;
import com.devsmile.repository.UserRepository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@NoArgsConstructor
//@RequiredArgsConstructor
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserDTO> getUsersList() {
		List<User> users = userRepository.findAll();
		log.info("3 Result users = {}", users.toString());
		
		List<UserDTO> usersDTO = users.stream().map(user -> UserTransformer.convertToUserDTO(user)).collect(Collectors.toList());
		log.info("3 Result users = {}", users.toString());
		
		return usersDTO;
	}

	public UserDTO getUserById(Integer id) {

		Optional<User> user = userRepository.findById(id);
		log.info("3 Result Optional<User> = {}", user.toString());

		if (user.isPresent()) {
			UserDTO userDTO = UserTransformer.convertToUserDTO(user.get());
			log.info("3 UserDTO: {}",userDTO);
			return userDTO;
		} else {
			return null;
		}
	}
}
