package com.nosto.currencyconverter.exceptionhandling;

public class InputsShouldNotBeSameException extends Exception {
	String errorMessage;

	private static final long serialVersionUID = 1L;

	public InputsShouldNotBeSameException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

}

