package com.devsmile.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.devsmile.model.User;
import com.devsmile.model.UserDTO;

public class UserTransformer {

	@Autowired
	private ModelMapper modelMapper;
	
	public UserDTO convert(User user) {
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
//		UserDTO userDTO = new UserDTOBuilder()
//				.id(user.getId())
//				.age(user.getAge())
//				.build();
		
		return userDTO;
	}

	public User convert(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
//		User user = new UserBuilder()
//				.id(userDTO.getId())
//				.age(userDTO.getAge())
//				.build();
		return user;
	}
}
