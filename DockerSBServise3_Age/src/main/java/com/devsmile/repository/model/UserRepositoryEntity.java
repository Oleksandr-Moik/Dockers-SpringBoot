package com.devsmile.repository.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name="User")
@ToString
public class UserRepositoryEntity {
	
	@Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer age;

    public UserRepositoryEntity() {
        super();
    }
}
