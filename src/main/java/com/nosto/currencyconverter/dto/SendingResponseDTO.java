package com.nosto.currencyconverter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SendingResponseDTO {

	/**
	 * Getters, setters and other constructors are loaded by @Data annotation
	 * provided by Lombok jar
	 * 
	 * @see <a href="https://projectlombok.org/">Project Lombok</a> for more
	 *      information.
	 * 
	 */

	private String sourceCurrency;
	private String targetCurrency;
	private double TargetCurrencyValue;
	private double number;
	private String totalValue;

}
