package com.devsmile.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
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
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class UserService {

	private final ServiceConnectiongConfiguration config;
	private final UserRepository userRepository;

	public List<UserDTO> getUsersList() {
		ResponseEntity<List<UserDTO>> response = getAgeServiseResponseList();
		log.info("[FirstName]-[UserService] getUsersList response = {};", response);

		if (response.getStatusCode() == HttpStatus.OK) {
			List<User> users = userRepository.findAll();
			log.info("[FirstName]-[UserService] Result users = {};", users.toString());

			List<UserDTO> usersDTO = users.stream().map(user -> UserTransformer.convertToUserDTO(user))
					.collect(Collectors.toList());

			List<UserDTO> usersDTOLastNameAge = response.getBody();
			for (int i = 0; i < users.size(); ++i) {
				usersDTO.get(i).setLastName(usersDTOLastNameAge.get(i).getLastName());
				usersDTO.get(i).setAge(usersDTOLastNameAge.get(i).getAge());
			}
			log.info("[FirstName]-[UserService] Result usersDTO = {};", usersDTO.toString());

			return usersDTO;
		} else {
			log.info("[FirstName]-[UserService] bad response");
			return null;
		}
	}

	public UserDTO getUserById(Integer id) {
		ResponseEntity<UserDTO> response = getAgeServiseResponseUser(id);

		log.info("[FirstName]-[UserService] getUserById response = {};", response);

		if (response.getStatusCode() == HttpStatus.OK) {
			User user = userRepository.findById(id).get();
			log.info("[FirstName]-[UserService] Result user = {};", user);

			UserDTO userDTO = UserTransformer.convertToUserDTO(user);
			userDTO.setLastName(response.getBody().getLastName());
			userDTO.setAge(response.getBody().getAge());

			log.info("[FirstName]-[UserService] Result UserDTO = {}", userDTO.toString());
			return userDTO;
		} else {
			return null;
		}
	}

	private ResponseEntity<UserDTO> getAgeServiseResponseUser(Integer id) {
		String url = String.format("http://%s:%s/user/%d", config.getHost(), config.getPort(), id);
		log.info("[FirstName]-[UserService] url = {}", url);
		ResponseEntity<UserDTO> response = new RestTemplate().exchange(url, HttpMethod.GET, PrapareHttpEntity(),
				UserDTO.class);
		return response;
	}

	private ResponseEntity<List<UserDTO>> getAgeServiseResponseList() {
		String url = String.format("http://%s:%s/user", config.getHost(), config.getPort());
		log.info("[FirstName]-[UserService] url = {}", url);
		ResponseEntity<List<UserDTO>> response = new RestTemplate().exchange(url, HttpMethod.GET, PrapareHttpEntity(),
				new ParameterizedTypeReference<List<UserDTO>>() {
				});
		return response;
	}

	private HttpEntity<String> PrapareHttpEntity() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return new HttpEntity<String>("parameters", httpHeaders);
	}
}
