package com.project.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.user.customvalidation.UniqueEmail;
import com.project.user.dto.UserDto;
import com.project.user.entity.User;
import com.project.user.exception.UserNotFoundedException;
import com.project.user.repository.UserRepository;

import jakarta.validation.ConstraintValidatorContext;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerNewUser(UserDto userDto, User user) {
		user.setUserName(userDto.getUserName());
		user.setUserEmail(userDto.getUserEmail());
		user.setUserPassword(userDto.getUserPassword());

		return userRepository.save(user);
	}
	
	@Override
	public Optional<User> findByUserEmail(String userEmail) {
		return userRepository.findByUserEmail(userEmail);
	}
	
}
