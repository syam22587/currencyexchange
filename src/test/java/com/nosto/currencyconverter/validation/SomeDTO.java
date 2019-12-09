package com.nosto.currencyconverter.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SomeDTO {

	@CurrencyValidator
	String source;
	
	String target;
}
