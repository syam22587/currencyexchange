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

import com.nosto.currencyconverter.dto.ReceivedResponse;
import com.nosto.currencyconverter.dto.SendingResponseDTO;
import com.nosto.currencyconverter.utils.NumberFormatter;

/**
 * @author SyamVoleti
 *
 */

@Service
@CacheConfig(cacheNames = { "currencyinfo" })
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

	private static final Logger log = LogManager.getLogger(CurrencyConverterServiceImpl.class);

	/**
	 * API Url value is fetched from properties file.
	 */
	@Value("${exchangeapi.url}")
	String apiUrl;

	/**
	 * exchangeapi.basecurrency property fetches default value.
	 */
	@Value("${exchangeapi.basecurrency}")
	String baseCurrency;

	/**
	 * RestTemplate is used as a bridge to consume external API 'exchangeratesapi'
	 * 
	 * @see <a href="https://exchangeratesapi.io/">https://exchangeratesapi.io/</a>
	 *      for more information about external API details
	 */
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	NumberFormatter numberFormatter;

	/**
	 * Static HttpEntity reference is used to get headers which are further used
	 * across the Service.
	 */
	private static HttpEntity<String> entity;

	static {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		entity = new HttpEntity<String>(headers);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nosto.currencyconverter.service.CurrencyConverterService#convertService(
	 * java.lang.String, java.lang.String, java.lang.Double)
	 */
	@Override
	@Cacheable
	public SendingResponseDTO convertService(String source, String target, Double number) {

		/*
		 * Below gets the response from External API and fetches the target currency
		 * details
		 */
		ReceivedResponse response = getCurrencyDetailsFromApi(source, target);
		double currentPrice = response.getRates().get(target).asDouble();
		SendingResponseDTO dto = new SendingResponseDTO();
		double total = currentPrice * number;

		log.info("Total value before the Number format conversion " + total);
		String formattedTotal = numberFormatter.convertNumberToCurrency(total, target.substring(0, 2));
		log.info("Total value after the Number format conversion " + formattedTotal);

		dto.setSourceCurrency(source);
		dto.setTargetCurrency(target);
		dto.setTargetCurrencyValue(currentPrice);
		dto.setNumber(number);
		dto.setTotalValue(formattedTotal);

		log.info("Total price =  " + total);
		log.info("DTO Response  " + response.toString());

		return dto;

	}

	/**
	 * @param actualUrl
	 * @return
	 */
	@Cacheable
	private ReceivedResponse getCurrencyDetailsFromApi(String source, String target) {
		String actualUrl = apiUrl + "?base=" + source + "&symbols=" + target;
		log.info("Url for external api consumption is : " + actualUrl);
		ReceivedResponse response = restTemplate.exchange(actualUrl, HttpMethod.GET, entity, ReceivedResponse.class)
				.getBody();
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
		 * Since our exchange API provides base currency as EUR as currency this will
		 * skip the validation logic by returning true
		 */
		if (currency.equals(baseCurrency))
			return true;

		/*
		 * Anything other than base currency will be checked remotely.
		 */
		ReceivedResponse response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, ReceivedResponse.class)
				.getBody();

		log.info("Status of '" + currency + "'  is  : " + response.getRates().has(currency));
		return response.getRates().has(currency);

	}

}
