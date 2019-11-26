package com.devsmile.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsmile.repository.model.UserRepositoryEntity;

@Repository
public interface UserRepository extends JpaRepository<UserRepositoryEntity, Integer> {
}
