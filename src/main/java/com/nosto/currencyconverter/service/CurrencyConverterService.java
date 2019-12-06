package com.nosto.currencyconverter.service;

import com.nosto.currencyconverter.dto.ConverterDto;

public interface CurrencyConverterService {

	public ConverterDto convertService(String source, String target, Double number ) ;

	public boolean isCurrencyExist(String currency); 
	
}
