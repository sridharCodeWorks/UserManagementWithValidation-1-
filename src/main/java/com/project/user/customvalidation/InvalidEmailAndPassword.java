package com.project.user.customvalidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.project.user.customvalidationimplementation.UserLogInValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = UserLogInValidation.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface InvalidEmailAndPassword {

	public String message() default "Invalid User Name and Password";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};
}
