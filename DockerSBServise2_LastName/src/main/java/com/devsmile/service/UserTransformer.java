package com.devsmile.service;

import com.devsmile.model.User;
import com.devsmile.model.UserDTO;

public class UserTransformer {
	
	public static UserDTO convert(User user) {
		return UserDTO.builder()
				.id(user.getId())
				.lastName(user.getLastName())
				.build();
	}

	public static User convert(UserDTO userDTO) {
		return User.builder()
				.id(userDTO.getId())
				.lastName(userDTO.getLastName())
				.build();
	}
}
