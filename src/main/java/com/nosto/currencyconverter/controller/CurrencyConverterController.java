package com.nosto.currencyconverter.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nosto.currencyconverter.dto.SendingResponseDTO;
import com.nosto.currencyconverter.exceptionhandling.InputsShouldNotBeSameException;
import com.nosto.currencyconverter.exceptionhandling.NotFoundException;
import com.nosto.currencyconverter.service.CurrencyConverterService;
import com.nosto.currencyconverter.validation.CurrencyValidator;

import io.github.sercasti.tracing.Traceable;

/**
 * @author SyamVoleti
 *
 */
@RestController
@RequestMapping("/currency")
@Validated
public class CurrencyConverterController {

	private static final Logger LOGGER = LogManager.getLogger(CurrencyConverterController.class);

	/**
	 * converterService acts as a service bridge to fetch data from External API
	 */
	@Autowired
	CurrencyConverterService converterService;

	/**
	 * @param source - Source Currency
	 * @param target - Target Currency
	 * @param number - Source Monetory Number of Source currency to be converted
	 * @return A response entity of type <SendingResponseDTO>
	 * @throws NotFoundException
	 * @throws InputsShouldNotBeSameException
	 * 
	 * @Traceable is a annotation used from Server Timing support project. @see
	 *            <a href=
	 *            "https://github.com/sercasti/spring-httpserver-timings">This</a>
	 *            for more information.
	 */
 
	@Traceable
	@GetMapping(value = "/converter/{source}/{target}/{number}")
	public ResponseEntity<SendingResponseDTO> convertCurrency(
			@PathVariable("source") @CurrencyValidator @Pattern(regexp = "[a-zA-Z]{3}") @NotBlank @Size(min = 3) String source,
			@PathVariable("target") @CurrencyValidator @Pattern(regexp = "[a-zA-Z]{3}") @NotBlank @Size(min = 3) String target,
			@PathVariable("number") Double number) throws NotFoundException, InputsShouldNotBeSameException {

		/*
		 * This if condition is written in conjunction to check if two inputs are same.
		 */
		
		LOGGER.info("skskskskskssksks") ; 
		if (source.equals(target)) {
			throw new InputsShouldNotBeSameException(
					" Source Currency and Target currency should not be same for the conversion.");
		}

		/*if (!converterService.isCurrencyExist(source.toUpperCase())
				|| !converterService.isCurrencyExist(target.toUpperCase())) {
			throw new NotFoundException(" Entered currency format is not supported or does not exist. Try again. ");
		}*/

		SendingResponseDTO val = converterService.convertService(source.toUpperCase(), target.toUpperCase(), number);
		
		LOGGER.info("sadfasdfasfasdf" + val) ; 

		return ResponseEntity.ok().body(val);

	}

}
