package com.devsmile.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.devsmile.ServiceConnectiongConfiguration;
import com.devsmile.model.User;
import com.devsmile.model.UserDTO;
import com.devsmile.model.UserResponseDTO;
import com.devsmile.repository.UserRepository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@NoArgsConstructor
//@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class UserService {

	@Autowired
	private ServiceConnectiongConfiguration config;
	

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<List<UserDTO>> getUsersList() {
		List<User> users = userRepository.findAll();
		log.info("2 Result users = {};", users.toString());
		
		List<UserDTO> usersDTO = users.stream().map(user -> UserTransformer.convertToUserDTO(user)).collect(Collectors.toList());
		log.info("2 Result usersDTO = {};", usersDTO.toString());
		
		return ResponseEntity.ok().body(usersDTO);
	}

	public UserDTO getUserById(Integer id) {
		ResponseEntity<UserResponseDTO> response = getAgeServiseResponse(id);

		log.info("2 getUserById response status = {};", response.getStatusCode());
		log.info("2 getUserById response body = {};", response.getBody());

		if (response.getStatusCode() == HttpStatus.OK) {
			UserDTO userDTO = UserTransformer.concatUsers(userRepository.findById(id).get(), response.getBody());
			log.info("2 Result UserDTO = {}", userDTO.toString());
			return userDTO;
		} else {
			return null;
		}
	}

	private ResponseEntity<UserResponseDTO> getAgeServiseResponse(Integer id) {
		String url = String.format("http://%s:%s/user/%d",config.getHost(),config.getPort(), id);
		log.info("url = {}",url);
		ResponseEntity<UserResponseDTO> response = 
				new RestTemplate().exchange(url, HttpMethod.GET,PrapareHttpEntity(),UserResponseDTO.class);
		return response;
	}
	
	private HttpEntity<String> PrapareHttpEntity() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		return new HttpEntity<String>("parameters", httpHeaders);
	}
}
