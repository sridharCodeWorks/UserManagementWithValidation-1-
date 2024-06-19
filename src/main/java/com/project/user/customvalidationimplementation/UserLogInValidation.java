package com.project.user.customvalidationimplementation;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.user.customvalidation.InvalidEmailAndPassword;
import com.project.user.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserLogInValidation implements ConstraintValidator<InvalidEmailAndPassword, String> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValid(String userEmail, ConstraintValidatorContext context) {
		if (userEmail == null) {
			return true;
		} else {
			return userRepository.existsByUserEmail(userEmail);
		}
	}
}
