package com.project.user.dto;

import com.project.user.customvalidation.UniqueEmail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {

	@NotBlank(message = "User Name is Mandatory")
	@Size(min = 2, message = "minimum size of user name should be atleast 2 characters")
	private String userName;

	@Email(message = "invalid email", regexp = "^(?=.*\\d{3,})[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
	@NotBlank(message = "User Email is Mandatory")
	@UniqueEmail(message = "This Email has already have an account")
	private String userEmail;

	@NotBlank(message = "Password is Mandatory")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "password should have ateast 8 alphabatics, one uper Camal case, one special character 3 numbers")
	private String userPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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
