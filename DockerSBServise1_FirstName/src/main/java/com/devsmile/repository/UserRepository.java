package com.devsmile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsmile.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
