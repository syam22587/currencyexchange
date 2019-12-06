package com.nosto.currencyconverter.service;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nosto.currencyconverter.dto.ConverterDto;

/**
 * @author lenovo
 *
 */
@Service
@CacheConfig(cacheNames = { "currencyinfo" })
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

	private static final Logger log = LogManager.getLogger(CurrencyConverterServiceImpl.class);

	/**
	 * API Url is fetched from properties file.
	 */
	@Value("${exchangeapi.url}")
	String apiUrl;

	@Value("${exchangeapi.basecurrency}")
	String baseCurrency;

	/**
	 * restTemplate is used a bridge to consume external API 'exchangeratesapi'
	 * 
	 * @see <a href="https://exchangeratesapi.io/">https://exchangeratesapi.io/</a>
	 *      for more information about external API details
	 */
	@Autowired
	RestTemplate restTemplate;

	/**
	 * Static entity reference is used to get headers which are further used across
	 * the Service.
	 */
	private static HttpEntity<String> entity;

	static {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		entity = new HttpEntity<String>(headers);
	}

	@Override
	@Cacheable
	public ConverterDto convertService(String source, String target, Double number) {

		log.info(" Source Currency : " + source + ", Target Currency : " + target + ", Amount to convert : " + number);

		String actualUrl = apiUrl + "?base=" + source + "&symbols=" + target;

		ConverterDto response = restTemplate.exchange(actualUrl, HttpMethod.GET, entity, ConverterDto.class).getBody();

		double price = response.getRates().get(target).asDouble();

		log.info("Total price =  " + price * number);
		log.info("asdfr " + response.toString());

		return response;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nosto.currencyconverter.service.CurrencyConverterService#isCurrencyExist(
	 * java.lang.String)
	 */
	@Override
	@Cacheable
	public boolean isCurrencyExist(String currency) {

		/*
		 * Since our exchange API provides base currency as EUR
		 */

		if (currency.equals(baseCurrency))
			return true;

		/**
		 * Anything other than base currency will be checked remotely.
		 */

		ConverterDto response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, ConverterDto.class).getBody();

		log.info("Status of '" + currency + "'  ************  " + response.getRates().has(currency));

		return response.getRates().has(currency);

		// return currency.matches("INR|EUR|USD|GBP|HUF|RUB|HRK|ISK|CAD");
	}

}
