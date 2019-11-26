package com.devsmile.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
	
	private Integer id;
    private Integer age;
    private String lastName;
}
