package com.nosto.currencyconverter.exception;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Constraint(validatedBy = CountryExistingValidation.class)
public @interface CountryExisting {

	String message() default "{CountryExisting}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
