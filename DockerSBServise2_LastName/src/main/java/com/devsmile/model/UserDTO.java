package com.devsmile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private Integer id;
    private Integer age;
    private String lastName;
    
    public UserDTO(Integer id, Integer age) {
    	this.id=id;
    	this.age=age;
    }

	public UserDTO(UserDTO userDTO) {
		this.id=userDTO.getId();
		this.age=userDTO.getAge();
		this.lastName=userDTO.getLastName();
	}
}
