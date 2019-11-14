package com.devsmile.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@Entity
@Table(name="User")
@Getter
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer age;

    public User() {
        super();
    }
    
    public User(UserBuilder builder) {
        this.age=builder.age;
        this.id=builder.id;
    }
    
    public UserBuilder builder() {
        return new User.UserBuilder();
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
        
        public User build() {
            return new User(this);
        }
    }
    
}