package com.nosto.currencyconverter.service;

public interface CurrencyConverterService {

	public String convertService(String source, String target, Double number ) ;

	public boolean isCurrencyExist(String currency); 
	
}
