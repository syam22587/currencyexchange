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

/**
 * @author SyamKumar
 *
 */
/**
 * @author lenovo
 *
 */
@Configuration
public class CurrencyConverterConfig {

	/**
	 * @param builder This is used to Connect external end points (REST API's)
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	/**
	 * @return Number Formatter bean
	 */
	@Bean
	public NumberFormatter getNumberFormatter() {
		return new NumberFormatter();
	}

	/**
	 * TraCing, TracingFilter & TracingInterceptor are used to generate Server Timer
	 * header informations.
	 * 
	 * @returns respective instantiated beans in Run time.
	 */

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
