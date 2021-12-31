package com.devsmile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsmile.domain.User;

@Repository
public interface UserRepos extends JpaRepository<User, Integer> {
	
}
