package com.nosto.currencyconverter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendingResponseDTO {

	private String source;
	private String target;
	private double number;
	private double total;
}
