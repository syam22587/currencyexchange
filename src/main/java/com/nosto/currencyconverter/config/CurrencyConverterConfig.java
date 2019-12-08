package com.nosto.currencyconverter.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.nosto.currencyconverter.utils.NumberFormatter;

import io.github.sercasti.tracing.config.TracingConfig;
import io.github.sercasti.tracing.core.Tracing;
import io.github.sercasti.tracing.filter.TracingFilter;
import io.github.sercasti.tracing.interceptor.TracingInterceptor;

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

	@Bean
	protected Tracing tracing() {
		return TracingConfig.createTracing();
	}

	@Bean
	protected TracingFilter tracingFilter() {
		return new TracingFilter();
	}

	@Bean
	protected TracingInterceptor tracingInterceptor() {
		return new TracingInterceptor(tracing());
	}
}
