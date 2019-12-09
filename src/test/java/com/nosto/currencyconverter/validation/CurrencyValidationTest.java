package com.nosto.currencyconverter.validation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.nosto.currencyconverter.controller.CurrencyConverterController;
import com.nosto.currencyconverter.dto.SendingResponseDTO;
import com.nosto.currencyconverter.service.CurrencyConverterService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyValidationTest {

	private MockMvc mockMvc;

	@InjectMocks
	private CurrencyConverterController controller;

	@MockBean
	private CurrencyConverterService cs;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void currencyValidatorTest() throws Exception {
		SendingResponseDTO dto = new SendingResponseDTO();

		String source = "GBPf";
		String target = "USD";
		Double number = 32.56;

		String apiURL = "/currency/converter/" + source + "/" + target + "/" + number;
		Mockito.when(cs.isCurrencyExist(Mockito.anyString())).thenReturn(false);

		mockMvc.perform(get(apiURL).accept(MediaType.TEXT_HTML)).andExpect(status().isOk()).andDo(print())
				.andReturn();

	}
}
