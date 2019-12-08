package com.nosto.currencyconverter.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nosto.currencyconverter.dto.TestDTO;

public class InputsShouldNotSameValidation implements ConstraintValidator<InputsShouldNotSameValidator, TestDTO> {

	private static final Logger LOGGER = LogManager.getLogger(InputsShouldNotSameValidation.class);

	@Override
	public boolean isValid(TestDTO dto, ConstraintValidatorContext context) {

		LOGGER.info("*******************************************SSSSSSS***************************") ; 
		return !dto.getSource().equals(dto.getTarget());
	}

}
