package com.nosto.currencyconverter.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author SyamVoleti
 *
 */
@SpringBootTest
public class NumberFormatterTest {

	/**
	 * @throws Exception
	 */
	@Test
	public void validConvertNumberToCurrencyTest() throws Exception {
		NumberFormatter nf = new NumberFormatter();

		Double inputNumber = 23713.05441;
		String currency = "IN";
		String actual = "Rs.23,713.05";

		String expected = nf.convertNumberToCurrency(inputNumber, currency);
		assertEquals(expected, actual);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void inValidConvertNumberToCurrencyTest() throws Exception {
		NumberFormatter nf = new NumberFormatter();

		Double inputNumber = 23700.05441;
		String currency = "IN";
		String actual = "Rs.23,713.05";

		String unexpected = nf.convertNumberToCurrency(inputNumber, currency);
		assertNotEquals(unexpected, actual);
	}

	/**
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void checkWithInvalidDoubleVariable() throws Exception {
		NumberFormatter nf = new NumberFormatter();

		Double inputNumber = 78.00;
		String currency = "";
		String actual = "Rs.23,713.05";

		String ex = nf.convertNumberToCurrency(inputNumber, currency);

	}

}
