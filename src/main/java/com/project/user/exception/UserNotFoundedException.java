package com.project.user.exception;

public class UserNotFoundedException extends Exception {

	public UserNotFoundedException(String userEmail, String userPassword) {
		super("user Not founded by email Id :"+userEmail+" and password "+userPassword);
	}
}
