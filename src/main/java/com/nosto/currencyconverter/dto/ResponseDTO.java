package com.nosto.currencyconverter.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseDTO<T> {

	private int status;

	@Builder.Default
	private String message = "Sucess ... !";

	private T body;

}
