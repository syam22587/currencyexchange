package com.nosto.currencyconverter.utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author SyamVoleti
 *
 */
public class NumberFormatter {

	/**
	 * @param number
	 * @param currency
	 * @return
	 */
	public String convertNumberToCurrency(double number, String currency) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("en", currency));
		String output = nf.format(number);
		System.out.println("Currency in Canada : " + output);
		return output;
	}
}
