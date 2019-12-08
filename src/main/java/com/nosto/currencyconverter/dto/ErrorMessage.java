package com.nosto.currencyconverter.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @author SyamVoleti
 * 
 * ErrorMessage is a Error response DTO used to send to client if an error or exception occurs.
 *
 */
@Data
public class ErrorMessage {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private HttpStatus status;
	private String message;
	private String errorMessage;

	/**
	 * Default constructor ErrorMessage() initializes the time of the error.
	 */
	public ErrorMessage() {
		timestamp = LocalDateTime.now();
	}

	/**
	 * @param status Sets HttpStatus code
	 */
	public ErrorMessage(HttpStatus status) {
		this();
		this.status = status;
	}

	/**
	 * @param status
	 * @param ex
	 */
	public ErrorMessage(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.errorMessage = ex.getLocalizedMessage();
	}
}
