package com.project.user.advaicer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.project.user.exception.UserNotFoundedException;

@ControllerAdvice
public class UserExceptionAdvaicer {

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFoundedException.class)
	public Map<String, String> userNotFoundedExceptionHandler(UserNotFoundedException userNotFoundedException) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", userNotFoundedException.getMessage());
		return errorMap;
	}
}
