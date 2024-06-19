package com.project.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.user.dto.UserDto;
import com.project.user.entity.User;

@Service
public interface UserService{

	public User registerNewUser(UserDto userDto, User user);

	public Optional<User> findByUserEmail(String userEmail);	
	
}
