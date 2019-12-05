package com.nosto.currencyconverter.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.nosto.currencyconverter.dto.ResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CurrencyExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstrainViolationException(Exception ex, WebRequest request) {
		
		log.error(ex.getMessage(), ex);
		
		System.out.println("Reached here....");

		ResponseDTO dto = ResponseDTO.builder()
				.message(ex.getMessage()).build();

		return ResponseEntity.badRequest().body(dto);

	}
}
