package com.devsmile.service.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class UserEntity {

	private Integer id;
	private Integer age;

    public UserEntity() {
        super();
    }
    
    public UserEntity(UserBuilder builder) {
        this.age=builder.age;
        this.id=builder.id;
    }
    
    public UserBuilder builder() {
        return new UserEntity.UserBuilder();
    }
    
    @ToString
    public static class UserBuilder{
        
    	private Integer id;
        private Integer age;
        
        public UserBuilder() {
            
        }
        
        public UserBuilder id(Integer id) {
        	this.id=id;
        	return this;
        }
        
        public UserBuilder age(Integer age) {
            this.age = age;
            return this;
        }
        
        public UserEntity build() {
            return new UserEntity(this);
        }
    }
    
}