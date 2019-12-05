package com.nosto.currencyconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author SyamKumar
 *
 */
@SpringBootApplication(scanBasePackages = { "com.nosto.currencyconverter" }, exclude = {
		SecurityAutoConfiguration.class })

public class CurrencyconverterApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CurrencyconverterApplication.class, args);
	}

	
}
