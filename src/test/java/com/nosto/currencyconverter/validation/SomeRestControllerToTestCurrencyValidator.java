package com.nosto.currencyconverter.validation;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/testValidator")
public class SomeRestControllerToTestCurrencyValidator {

	@GetMapping("/{source}/{target}")
	public void thisMethodIsForValidatorTesting(@Valid @RequestBody SomeDTO dto) {

		System.out.println("Hi..");
	}

}
