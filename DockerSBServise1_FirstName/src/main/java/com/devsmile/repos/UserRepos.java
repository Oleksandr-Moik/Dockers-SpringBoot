package com.devsmile.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsmile.domain.User;

public interface UserRepos extends JpaRepository<User, Integer> {

}
