package com.devsmile.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

	@Value("${jdbc.url}")
	private static String AGE_SERVICE_URL;

	@Autowired
	private UserRepository userRepository;

	public List<UserDTO> getUsersList() {
		List<User> users = userRepository.findAll();
		log.info("2 Result List = {};", users.toString());
		return users.stream().map(user -> UserTransformer.convert(user)).collect(Collectors.toList());
	}

	public ResponseEntity<UserDTO> getUserById(Integer id) {
		ResponseEntity<User> response = exchange(id);

		log.info("2 getUserById status = {};", response.getStatusCode());

		if (response.getStatusCode() == HttpStatus.OK) {

			UserDTO userDTO = UserTransformer.convert(response.getBody());
			log.info("2 Result UserDTO = {}", userDTO.toString());

			return userDTO;
		} else {
			return null;
		}
	}

	private ResponseEntity<User> exchange(Integer id) {
		return new RestTemplate().exchange(String.format(AGE_SERVICE_URL + "/{}", id), 
				HttpMethod.GET, PrapareHttpEntity(), User.class);
	}
	
	private HttpEntity<String> PrapareHttpEntity() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		return new HttpEntity<String>("parameters", httpHeaders);
	}
}
