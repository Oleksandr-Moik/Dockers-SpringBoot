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
	
	public List<UserDTO> getUsers(){
		List<User> users = userRepository.findAll();
		log.info("3 Result List = {}",users.toString());
		return users.stream()
			        .map(user -> UserTransformer.convert(user))
			        .collect(Collectors.toList());
	}
	
	public UserDTO  getUser(Integer id){		
		Optional<User> user = userRepository.findById(id); 
		log.info("3 Result Optional<User> = {}", user.toString());
    	if (user.isPresent()) return UserTransformer.convert(user.get());
    	else return null;
	}
}
