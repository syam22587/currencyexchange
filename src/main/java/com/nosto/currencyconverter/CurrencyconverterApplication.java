package com.nosto.currencyconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author SyamKumar
 *
 */
@SpringBootApplication(scanBasePackages = { "com.nosto.currencyconverter" }, exclude = {
		SecurityAutoConfiguration.class })
@EnableCaching
public class CurrencyconverterApplication {

	/**
	 * @param args Command Line Arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(CurrencyconverterApplication.class, args);
	}

}
