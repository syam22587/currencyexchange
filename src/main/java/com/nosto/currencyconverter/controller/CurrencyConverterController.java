package com.nosto.currencyconverter.controller;

import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nosto.currencyconverter.exception.CountryExisting;
import com.nosto.currencyconverter.service.CurrencyConverterService;

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
	 */
	@GetMapping(value = "/converter/{source}/{target}/{number}")
	public String convertCurrency(@PathVariable("source") @CountryExisting String source,
			@PathVariable("target") @CountryExisting String target, @PathVariable("number") Double number) {

		// String val = "?qretbase=" + source + "&symbols=" + target + "&num=" + number;
		String val = converterService.convertService(source, target, number);

		return val;

	}

}
