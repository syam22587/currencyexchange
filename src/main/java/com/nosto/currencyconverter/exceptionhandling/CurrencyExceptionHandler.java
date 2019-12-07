package com.nosto.currencyconverter.exceptionhandling;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author SyamVoleti
 *
 */
@Slf4j
@ControllerAdvice
public class CurrencyExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ErrorMessage> handleConstrainViolationException(Exception ex, WebRequest request) {

		log.error("************ error reached **************");

		ErrorMessage em = new ErrorMessage();
		em.setStatus(HttpStatus.BAD_REQUEST);
		em.setMessage("There found a bad request :( ");
		em.setErrorMessage(ex.getLocalizedMessage());

		return ResponseEntity.badRequest().body(em);

	}

	/**
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorMessage> notFoundException(final NotFoundException ex) {
		ErrorMessage em = new ErrorMessage();
		em.setStatus(HttpStatus.NOT_FOUND);
		em.setMessage("No Handler found for the entered Request ");
		em.setErrorMessage(ex.getLocalizedMessage());

		return ResponseEntity.badRequest().body(em);

	}

	/**
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		ErrorMessage em = new ErrorMessage();
		em.setStatus(HttpStatus.BAD_REQUEST);
		em.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));

		em.setErrorMessage(ex.getMessage());
		return ResponseEntity.badRequest().body(em);
	}

	/**
	 * Handle NoHandlerFoundException.
	 *
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return
	 */
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorMessage em = new ErrorMessage();
		em.setStatus(HttpStatus.BAD_GATEWAY);
		em.setMessage(String.format("Received an invalid Request %s", ex.getHttpMethod(), ex.getRequestURL()));
		em.setErrorMessage(ex.getMessage());
		return ResponseEntity.badRequest().body(em);
	}

	/**
	 * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid
	 * validation.
	 *
	 * @param ex      the MethodArgumentNotValidException that is thrown when @Valid
	 *                validation fails
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the ApiError object
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorMessage em = new ErrorMessage();
		em.setStatus(HttpStatus.BAD_GATEWAY);
		em.setMessage("Validation error");

		em.setErrorMessage(ex.getBindingResult().getFieldErrors().toString());
		// ex.getBindingResult().getFieldErrors() ;
		return ResponseEntity.badRequest().body(em);
	}
}