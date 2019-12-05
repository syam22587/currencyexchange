package com.nosto.currencyconverter.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author lenovo
 *
 */
@Service
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

	/**
	 * API Url is fetched from properties file.
	 */
	@Value("${exchangeapi.url}")
	String apiUrl;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public String convertService(String source, String target, Double number) {

		System.out.println("s : " + source + ", t : " + target + ", n : " + number);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		String response = restTemplate
				.exchange(apiUrl + "?base=" + source + "&symbols=" + target, HttpMethod.GET, entity, String.class)
				.getBody();
		return response;

	}

	@Override
	public boolean isCurrencyExist(String currency) {
		return currency.matches("INR|EUR|USD|GBP|HUF|RUB|HRK|ISK|CAD");
	}

	 
}
