package com.devsmile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devsmile.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "SELECT age FROM User WHERE id=:id")
	public Integer getAgeByUserId(@Param("id") Integer id);
}
