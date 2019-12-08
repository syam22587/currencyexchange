package com.nosto.currencyconverter.exceptionhandling;

import lombok.Getter;

/**
 * @author SyamVoleti
 *
 *         NotFoundException will be raised if a currency doesn't supported.
 */

@Getter
public class NotFoundException extends Exception {

	private String errorMessage;
	private static final long serialVersionUID = 1L;

	public NotFoundException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
}
