package com.nosto.currencyconverter.validation;

import static org.mockito.Mockito.when;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.nosto.currencyconverter.service.CurrencyConverterService;

@SpringBootTest
public class CurrencyValidationTest {

	private Validator validator;
	
	@Mock
	CurrencyConverterService cs ; 
	 
    @BeforeAll
    public void setUp() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    @Test
    public void currencyValidTest() throws Exception {
    	String currency = "EUR" ; 
    	when(cs.isCurrencyExist(currency)).thenReturn(true); 
    //	Set<ConstraintViolation> violations = validator.validate(form);
    	 
    }
    
    
    /*
     * 
     * @Test
    public void shouldMarkPasswordsAsInvalid() throws Exception {
        //given
        UserSignUpForm form = new UserSignUpForm("daniel", "pass", "differentPass");
        //when
        Set<ConstraintViolation<UserSignUpForm>> violations = validator.validate(form);
        //then
        assertEquals(1, violations.size());Cur
    }
     */
}
