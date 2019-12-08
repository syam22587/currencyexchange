package com.nosto.currencyconverter.validation;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nosto.currencyconverter.service.CurrencyConverterService;

/**
 * @author lenovo
 *
 */

@Component
public class CurrencyValidation implements ConstraintValidator<CurrencyValidator, String> {

	@Override
	public void initialize(CurrencyValidator constraint) {
	}

	/**
	 * 
	 */
	@Autowired
	CurrencyConverterService currencyService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String currency, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return Objects.isNull(currency) || currencyService.isCurrencyExist(currency.toUpperCase());
	}

}
