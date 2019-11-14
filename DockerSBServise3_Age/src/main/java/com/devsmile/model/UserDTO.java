package com.devsmile.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@Getter
@ToString
public class UserDTO {
	
	private Integer id;
    private Integer age;

    public UserDTO() {
        super();
    }
    
    public UserDTO(UserDTOBuilder builder) {
        this.age=builder.age;
        this.id=builder.id;
    }
    
    public UserDTOBuilder builder() {
        return new UserDTO.UserDTOBuilder();
    }
    
    @ToString
    public static class UserDTOBuilder{
        
    	private Integer id;
        private Integer age;
        
        public UserDTOBuilder() {
            
        }
        
        public UserDTOBuilder id(Integer id) {
        	this.id=id;
        	return this;
        }
        
        public UserDTOBuilder age(Integer age) {
            this.age = age;
            return this;
        }
        
        public UserDTO build() {
            return new UserDTO(this);
        }
    }
}
