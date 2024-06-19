package com.project.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUserEmailAndUserPassword(String userEmail, String userPassword);

	public boolean existsByUserEmail(String userEmail);

	public Optional<User> findByUserEmail(String userEmail);

}
