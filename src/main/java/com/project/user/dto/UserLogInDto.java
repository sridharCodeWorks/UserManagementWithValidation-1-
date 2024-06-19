package com.project.user.dto;

import com.project.user.customvalidation.InvalidEmailAndPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserLogInDto {

	@NotBlank(message = "Email is required")
	@Email(message = "Not a Valid Email")
	@InvalidEmailAndPassword(message = "Invalid User Name or Password")
	private String userEmail;
	@NotBlank(message = "Password is Requied")
	// @Pattern(regexp =
	// "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message
	// = "password should have ateast 8 alphabatics, one uper Camal case, one
	// special character 3 numbers")
	private String userPassword;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
