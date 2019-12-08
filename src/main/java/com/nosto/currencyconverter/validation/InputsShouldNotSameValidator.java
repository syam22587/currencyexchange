package com.nosto.currencyconverter.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = InputsShouldNotSameValidation.class)
public @interface InputsShouldNotSameValidator {
	String message() default "Source and Target should not be same. ";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
