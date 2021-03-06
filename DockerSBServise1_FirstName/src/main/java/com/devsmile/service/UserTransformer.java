package com.devsmile.service;

import com.devsmile.domain.User;
import com.devsmile.domain.dto.UserDTO;

public class UserTransformer {
	
	public static UserDTO convertToUserDTO(User user) {
		return UserDTO.builder()
				.id(user.getId())
				.firstName(user.getFirstName())
				.build();
	}

	public static User convertToUser(UserDTO userDTO) {
		return User.builder()
				.id(userDTO.getId())
				.firstName(userDTO.getFirstName())
				.build();
	}
	
}
