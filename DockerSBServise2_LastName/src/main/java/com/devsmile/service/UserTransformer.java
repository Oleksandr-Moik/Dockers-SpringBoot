package com.devsmile.service;

import com.devsmile.model.User;
import com.devsmile.model.UserDTO;
import com.devsmile.model.UserResponseDTO;

public class UserTransformer {
	
	public static UserDTO convertToUserDTO(User user) {
		return UserDTO.builder()
				.id(user.getId())
				.lastName(user.getLastName())
				.build();
	}

	public static User convertToUser(UserDTO userDTO) {
		return User.builder()
				.id(userDTO.getId())
				.lastName(userDTO.getLastName())
				.build();
	}
	
	public static UserDTO convertToUserDTO(UserResponseDTO user) {
		return UserDTO.builder()
				.id(user.getId())
				.age(user.getAge())
				.build();
	}
	
	public static UserDTO concatUsers(User userDB, UserResponseDTO userResp) {
		return UserDTO.builder()
				.id(userDB.getId())
				.lastName(userDB.getLastName())
				.age(userResp.getAge())
				.build();
	}
}
