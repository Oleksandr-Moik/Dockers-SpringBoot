package com.devsmile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	@Autowired
	private UserTransformer userTransformer;
	
	public ResponseEntity<List<User>> getUsers(){
		return null;
	}
	
	public ResponseEntity<UserDTO>  getUser(Integer id){		
//		Optional<UserDTO> userDTO = userRepository.findById(id); 
		Optional<User> user = userRepository.findById(id); 

    	if (user.isPresent()) {
            UserDTO userDTO = userTransformer.convert(user.get());
            log.info("Service3-Age GET: {}",userDTO.toString());
            return ResponseEntity.ok().body(userDTO);
        } else {
            log.error("Service-3-Age GET: user with id={} not found.",id);
            return ResponseEntity.badRequest().body(null);
        }
	}
	
	public ResponseEntity<User> newUser(User user){
		return null;
	}
	
	public ResponseEntity<User> editUser(User user, Integer id){
		return null;
	}
	
	public ResponseEntity<User> removeUser(Integer id){
		return null;
	}
}
