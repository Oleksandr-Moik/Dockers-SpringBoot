package com.devsmile.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
	
	private Integer id;
    private Integer age;

    public UserDTO() {
        super();
    }

	public UserDTO(Integer id, Integer age) {
		super();
		this.id = id;
		this.age = age;
	}
}
