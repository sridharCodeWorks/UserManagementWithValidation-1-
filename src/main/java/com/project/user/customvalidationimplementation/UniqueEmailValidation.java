package com.project.user.customvalidationimplementation;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.user.customvalidation.UniqueEmail;
import com.project.user.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidation implements ConstraintValidator<UniqueEmail, String> {
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValid(String userEmail, ConstraintValidatorContext context) {
		if (userEmail == null) {
			return true;
		}
		return !userRepository.existsByUserEmail(userEmail);
	}
}
