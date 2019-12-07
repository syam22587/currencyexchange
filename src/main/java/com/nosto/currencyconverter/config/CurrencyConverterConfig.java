package com.nosto.currencyconverter.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.nosto.currencyconverter.utils.NumberFormatter;

@Configuration
public class CurrencyConverterConfig {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public NumberFormatter getNumberFormatter() {
		return new NumberFormatter();
	}
}
