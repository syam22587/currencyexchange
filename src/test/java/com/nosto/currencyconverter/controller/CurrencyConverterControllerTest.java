package com.nosto.currencyconverter.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.nosto.currencyconverter.dto.SendingResponseDTO;
import com.nosto.currencyconverter.service.CurrencyConverterService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyConverterControllerTest {

	private static final Logger LOGGER = LogManager.getLogger(CurrencyConverterControllerTest.class);

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

		Mockito.when(cs.convertService(Mockito.anyString(), Mockito.anyString(), Mockito.anyDouble())).thenReturn(dto);

		MvcResult result = mockMvc.perform(get(apiURL).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print()).andReturn();

	}

	@Test
	public void constraintViolationExceptionTest() throws Exception {
		SendingResponseDTO dto = new SendingResponseDTO();

		String source = "";
		String target = "USD";
		Double number = 32.56;

		String apiURL = "/currency/converter/" + source + "/" + target + "/" + number;

		Mockito.when(cs.convertService(Mockito.anyString(), Mockito.anyString(), Mockito.anyDouble()))
				.thenThrow(ConstraintViolationException.class);

		mockMvc.perform(get(apiURL).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andReturn();

	}

	@Test
	public void endPointNotReachableTest() throws Exception {
		String wrongApiURL = "/currencys/converter/INR/USD";
		mockMvc.perform(get(wrongApiURL)).andExpect(status().isNotFound());
	}

	@Test
	public void checkIfMediaTypeAcceptsJson() throws Exception {
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

		Mockito.when(cs.convertService(Mockito.anyString(), Mockito.anyString(), Mockito.anyDouble())).thenReturn(dto);

		mockMvc.perform(get(apiURL).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print())
				.andReturn();

	}
	
}
