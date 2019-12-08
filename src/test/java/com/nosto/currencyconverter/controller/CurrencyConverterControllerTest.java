package com.nosto.currencyconverter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import com.nosto.currencyconverter.dto.SendingResponseDTO;
import com.nosto.currencyconverter.service.CurrencyConverterService;

@SpringBootTest
//@ExtendWith(MockitoJUnitRunner.class)
//@WebMvcTest(CurrencyConverterController.class)
public class CurrencyConverterControllerTest {

	@Mock
	CurrencyConverterService cs;

	@Autowired
	CurrencyConverterService CurrencyConverterService;

	@Mock
	RestTemplate rt;

	@MockBean
	CurrencyConverterController controller ; 
	
	@Test
	public void convertServiceTest() {
		when(cs.convertService("GBP", "USD", 2.00)).thenReturn(new SendingResponseDTO("GBP", "EUR", 2.00, "$187.89"));

		assertEquals("GBP", cs.convertService("GBP", "USD", 2.00).getSource());
		assertEquals("EUR", cs.convertService("GBP", "USD", 2.00).getTarget());
		assertEquals(2.00, cs.convertService("GBP", "USD", 2.00).getNumber());
		assertEquals("$187.89", cs.convertService("GBP", "USD", 2.00).getTotal());
	}
	
	@Test
	public void convertServiceControllerTest() {
		when(cs.convertService("GBP", "USD", 2.00)).thenReturn(new SendingResponseDTO("GBP", "EUR", 2.00, "$187.89"));
		when(cs.isCurrencyExist("GBP")).thenReturn(true) ;
		when(cs.isCurrencyExist("USD")).thenReturn(true) ;
		
		
		
	}

}
