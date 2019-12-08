package com.nosto.currencyconverter.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.nosto.currencyconverter.validation.CurrencyValidator;
import com.nosto.currencyconverter.validation.InputsShouldNotSameValidator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@InputsShouldNotSameValidator(message ="Should not be same ")
public class TestDTO {

	@Pattern(regexp = "[a-zA-Z]{3}")
	@NotBlank
	@Size(min = 3)
	@CurrencyValidator
	private String source;

	@Pattern(regexp = "[a-zA-Z]{3}")
	@NotBlank
	@Size(min = 3)
	@CurrencyValidator
	private String target;

	@NotNull
	private Double number;

}
