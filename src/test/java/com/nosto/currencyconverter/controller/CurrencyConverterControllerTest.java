package com.nosto.currencyconverter.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nosto.currencyconverter.dto.SendingResponseDTO;
import com.nosto.currencyconverter.service.CurrencyConverterService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CurrencyConverterControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private CurrencyConverterController controller;

	//@Mock
	@Spy
	private CurrencyConverterService cs;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}

	@Test
	public void statusCheckTest() throws Exception {
		SendingResponseDTO dto = new SendingResponseDTO();

		String source = "GBP";
		String target = "USD";
		Double number = 32.56;

		dto.setSourceCurrency(source);
		dto.setTargetCurrency(target);
		dto.setNumber(number);
		dto.setTargetCurrencyValue(1.3136300664);
		dto.setTotalValue("$42.77");

		String apiURL = "/currency/converter/" + source + "/" + target + "/" + number;

		Mockito.when(cs.convertService(source, target, number)).thenReturn(dto);

		MvcResult result = mockMvc.perform(get(apiURL).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print()).andReturn();

		/*
		 * String content = result.getResponse().getContentAsString(); // verify the
		 * response string.
		 * 
		 * System.out.println("*************^^^^^^^^^^^^^^ " + content);
		 */

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
