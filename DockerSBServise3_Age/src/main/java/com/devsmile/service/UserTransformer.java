package com.devsmile.service;

import com.devsmile.model.User;
import com.devsmile.model.UserDTO;

public class UserTransformer {

	public static UserDTO convertToUserDTO(User user) {
		return UserDTO.builder().age(user.getAge()).id(user.getId()).build();
	}

	public static User convertToUser(UserDTO userDTO) {
		return User.builder().age(userDTO.getAge()).id(userDTO.getId()).build();
	}
}
