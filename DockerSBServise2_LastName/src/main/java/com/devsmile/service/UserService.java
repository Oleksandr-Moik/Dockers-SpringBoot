package com.devsmile.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.devsmile.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
//@NoArgsConstructor
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class UserService {

	@Value("{service.age.url}")
	private String AGE_SERVICE_URL;
	
	@Autowired
	private ServiceConnectiongConfiguration config;
	

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<List<UserDTO>> getUsersList() {
		List<User> users = userRepository.findAll();
		log.info("2 Result users = {};", users.toString());
		
		List<UserDTO> usersDTO = users.stream().map(user -> UserTransformer.convert(user)).collect(Collectors.toList());
		log.info("2 Result usersDTO = {};", usersDTO.toString());
		
		return ResponseEntity.ok().body(usersDTO);
	}

	public UserDTO getUserById(Integer id) {
		ResponseEntity<UserDTO> response = getAgeServiseResponse(id);

		log.info("2 getUserById status = {};", response.getStatusCode());

		if (response.getStatusCode() == HttpStatus.OK) {
			UserDTO userDTO = UserTransformer.convert(userRepository.findById(id).get());
			UserDTO respUserDTO = response.getBody();
			userDTO.setAge(respUserDTO.getAge());
			
			log.info("2 Result UserDTO = {}", userDTO.toString());
			return userDTO;
		} else {
			return null;
		}
	}

	private ResponseEntity<UserDTO> getAgeServiseResponse(Integer id) {
		ResponseEntity<UserDTO> response = 
				new RestTemplate()
				.exchange(String.format("http:/{}:{}/user/{}",config.getHost(),config.getPort(), id),
						HttpMethod.GET,
						PrapareHttpEntity(),
						UserDTO.class);
		return response;
//		return new RestTemplate().exchange(String.format(AGE_SERVICE_URL + "/{}", id), 
//				HttpMethod.GET, PrapareHttpEntity(), UserDTO.class);
	}
	
	private HttpEntity<String> PrapareHttpEntity() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		return new HttpEntity<String>("parameters", httpHeaders);
	}
}
