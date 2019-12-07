package com.nosto.currencyconverter.exceptionhandling;

import lombok.Getter;

@Getter
public class NotFoundException extends Exception {
	
	private String errorMessage ; 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String errorMessage) {
		super();
		this.errorMessage = errorMessage ; 
	}
}
