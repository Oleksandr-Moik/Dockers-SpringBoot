package com.devsmile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.devsmile.controller.UserRestController;
import com.devsmile.domain.dto.UserDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DockerSbServiseFirstName1ApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRestController controller;

	@Test
	public void test() throws Exception {
		this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk())
				.andExpect((ResultMatcher) content().string(containsString("age")));
	}

	// @Test
	public void userDtoAgeTest() {
		UserDTO user = new UserDTO(1, 23, "Moik", "Alex");
		assertThat(user.getAge()).as("check %s`s age", user.getFirstName()).isEqualTo(24);
	}

	// @Test
	public void TEST_1() {
		List<UserDTO> users = new ArrayList<UserDTO>();
		UserDTO Petrenko = new UserDTO(1, 23, "Petrenko", "Petrov");
		UserDTO Sidorov = new UserDTO(2, 16, "Sidorov", "Sergey");
		UserDTO Chehnov = new UserDTO(3, 20, "Chehnov", "Ivan");
		UserDTO Artur6 = new UserDTO(6, 25, "Artur6", "Crach6");

		users.add(Petrenko);
		users.add(Sidorov);
		users.add(Chehnov);
		users.add(Artur6);

	}

}
