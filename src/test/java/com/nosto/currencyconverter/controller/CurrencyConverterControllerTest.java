package com.nosto.currencyconverter.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nosto.currencyconverter.service.CurrencyConverterService;
import com.nosto.currencyconverter.service.CurrencyConverterServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CurrencyConverterControllerTest {

	@TestConfiguration
	static class currencyConverterConfig {

		@Bean
		public CurrencyConverterService currencyConverterService() {
			return new CurrencyConverterServiceImpl();
		}
	}

	@Autowired
	private CurrencyConverterService currencyConverterService;

}
