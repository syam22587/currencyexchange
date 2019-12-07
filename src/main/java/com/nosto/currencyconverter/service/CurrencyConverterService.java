package com.nosto.currencyconverter.service;

import com.nosto.currencyconverter.dto.SendingResponseDTO;

public interface CurrencyConverterService {

	public SendingResponseDTO convertService(String source, String target, Double number ) ;

	public boolean isCurrencyExist(String currency); 
	
}
