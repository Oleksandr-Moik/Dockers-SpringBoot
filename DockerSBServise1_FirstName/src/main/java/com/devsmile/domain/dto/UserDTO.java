package com.devsmile.domain.dto;

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
    private String firstName;
    
    public UserDTO(Integer id, Integer age, String lastName) {
    	this.id=id;
    	this.age=age;
    	this.lastName=lastName;
    }
}
