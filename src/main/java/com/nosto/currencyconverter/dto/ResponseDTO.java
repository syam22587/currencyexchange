package com.nosto.currencyconverter.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseDTO<T> {

	// private int status;
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")

	@Builder.Default
	private String message = "Sucess ... !";

	private T body;

}
