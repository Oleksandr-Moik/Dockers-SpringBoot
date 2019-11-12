package com.devsmile.service;

import org.springframework.context.annotation.Bean;

import com.devsmile.repository.model.UserRepositoryEntity;
import com.devsmile.service.model.UserEntity;

public class UserTransformer {

	@Bean
	public UserEntity convertToEntity(UserRepositoryEntity userRepositoryEntity) {
		UserEntity user = new UserEntity()
				.builder()
				.age(userRepositoryEntity.getAge())
				.build();
		return user;
	}
	
	@Bean
	public UserRepositoryEntity convertToDTO(UserEntity userEntity) {
		UserRepositoryEntity user = new UserRepositoryEntity();
		user.setAge(userEntity.getAge());
		return user;
	}
	
}
