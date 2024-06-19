package com.project.user.customvalidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.project.user.customvalidationimplementation.UniqueEmailValidation;
import com.project.user.service.UserServiceImplementation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = UniqueEmailValidation.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

	public String message() default "This Email has already have an account";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};
}
