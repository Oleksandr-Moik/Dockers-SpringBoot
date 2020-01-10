package com.devsmile.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.devsmile.config.ServiceConnectiongConfig;
import com.devsmile.domain.User;
import com.devsmile.domain.dto.UserDTO;
import com.devsmile.repos.UserRepos;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class UserService {

	private final ServiceConnectiongConfig config;
	private final UserRepos userRepos;

	public List<UserDTO> getUsersList() {
		List<UserDTO> usersDTOLastNameAge = getServiseResponseList();
		log.info("[FirstName]-[UserService] getUsersList response = {};", usersDTOLastNameAge);

		if (usersDTOLastNameAge != null) {
			List<User> users = userRepos.findAll();
			log.info("[FirstName]-[UserService] Result users = {};", users.toString());

			List<UserDTO> userDTOs = users.stream().map(user -> UserTransformer.convertToUserDTO(user))
					.collect(Collectors.toList());
			int p = 0;
			for (UserDTO userDTO : userDTOs) {
				userDTO.setLastName(usersDTOLastNameAge.get(p).getLastName());
				userDTO.setAge(usersDTOLastNameAge.get(p).getAge());
				++p;
			}

			log.info("[FirstName]-[UserService] Result usersDTO = {};", userDTOs.toString());

			return userDTOs;
		} else {
			log.info("[FirstName]-[UserService] bad response");
			return null;
		}
	}

	public UserDTO getUserById(Integer id) {
		UserDTO responseUserDTO = getServiseResponseUser(id);
		log.info("[FirstName]-[UserService] getUserById response = {};", responseUserDTO);

		if (responseUserDTO != null) {
			User user = userRepos.findById(id).get();
			log.info("[FirstName]-[UserService] User = {};", user);

			UserDTO userDTO = UserTransformer.convertToUserDTO(user);
			userDTO.setLastName(responseUserDTO.getLastName());
			userDTO.setAge(responseUserDTO.getAge());

			log.info("[FirstName]-[UserService] return userDTO = {}", userDTO.toString());
			return userDTO;
		} else {
			return null;
		}
	}

	private UserDTO getServiseResponseUser(Integer id) {
		String url = String.format("http://%s:%s/user/%d", config.getHost(), config.getPort(), id);
		log.info("[FirstName]-[UserService] url = {}", url);
		return new RestTemplate().getForEntity(url, UserDTO.class).getBody();
	}

	private List<UserDTO> getServiseResponseList() {
		String url = String.format("http://%s:%s/user", config.getHost(), config.getPort());
		log.info("[FirstName]-[UserService] url = {}", url);
		UserDTO[] response = new RestTemplate().getForEntity(url,UserDTO[].class).getBody();
		return Arrays.asList(response);
	}
}
