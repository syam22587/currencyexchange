package com.nosto.currencyconverter.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nosto.currencyconverter.dto.SendingResponseDTO;
import com.nosto.currencyconverter.exceptionhandling.NotFoundException;
import com.nosto.currencyconverter.service.CurrencyConverterService;
import com.nosto.currencyconverter.validation.CurrencyValidator;

/**
 * @author lenovo
 *
 */
@RestController
@RequestMapping("/currency")
@Validated
public class CurrencyConverterController {

	private static final Logger LOGGER = LogManager.getLogger(CurrencyConverterController.class);

	/**
	 * converterService
	 */
	@Autowired
	CurrencyConverterService converterService;

	/**
	 * @param source
	 * @param target
	 * @param number
	 * @return
	 * @throws NotFoundException
	 */
	@GetMapping(value = "/converter/{source}/{target}/{number}")
	public ResponseEntity<SendingResponseDTO> convertCurrency(
			@PathVariable("source") @CurrencyValidator @Pattern(regexp = "[a-zA-Z]{3}") @NotBlank @Size(min = 3) String source,
			@PathVariable("target") @CurrencyValidator @Pattern(regexp = "[a-zA-Z]{3}") @NotBlank @Size(min = 3) String target,
			@PathVariable("number") Double number) throws NotFoundException {

		if (!converterService.isCurrencyExist(source) || !converterService.isCurrencyExist(target)) {
			throw new NotFoundException(" Entered currency format is not supported or does not exist. Try again. ");
		}

		// String val = "?qretbase=" + source + "&symbols=" + target + "&num=" + number;
		SendingResponseDTO val = converterService.convertService(source.toUpperCase(), target.toUpperCase(), number);

		return ResponseEntity.ok().body(val);

	}

}
