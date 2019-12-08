package com.nosto.currencyconverter;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CurrencyconverterApplicationTests {

	String hi = "test";

	@Test
	void whenCallingSayHello_thenReturnHello() {
		assertTrue(hi.equals("test"));
	}
}
