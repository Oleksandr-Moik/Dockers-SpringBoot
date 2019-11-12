package com.devsmile.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devsmile.repository.dao.UserRepository;
import com.devsmile.repository.model.UserRepositoryEntity;
import com.devsmile.service.model.UserEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<UserEntity>  getUserById(Integer id){		
		Optional<UserRepositoryEntity> userDTO = userRepository.findById(id); 
		
    	if (userDTO.isPresent()) {
            UserEntity userEntity = new UserTransformer().convertToEntity(userDTO.get());
            log.info("Service3-Age GET: {}",userEntity.toString());
            return ResponseEntity.ok().body(userEntity);
        } else {
            log.error("Service3-Age GET: user with id={} not found.",id);
            return ResponseEntity.badRequest().body(null);
        }
	}
}
