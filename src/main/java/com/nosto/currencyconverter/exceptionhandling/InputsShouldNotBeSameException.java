package com.nosto.currencyconverter.exceptionhandling;

/**
 * @author SyamVoleti
 *
 *         InputsShouldNotBeSameException will be raised if both source and
 *         target input currencies are same.
 */
public class InputsShouldNotBeSameException extends Exception {

	private String errorMessage;
	private static final long serialVersionUID = 1L;

	public InputsShouldNotBeSameException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
}
