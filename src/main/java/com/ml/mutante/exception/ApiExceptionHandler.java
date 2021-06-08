package com.ml.mutante.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@ControllerAdvice
public class ApiExceptionHandler {

	private final Logger log =  LoggerFactory.getLogger(this.getClass());


	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ServiceException.class, NullPointerException.class})
	public ResponseEntity<Object> handleInternalServerError(Exception e) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ApiException apiException = new ApiException(
				"Ha ocurrido un error inesperado",
				HttpStatus.INTERNAL_SERVER_ERROR, 
				LocalDateTime.now(), null);
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(apiException, status);

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({BusinessException.class})
	public ResponseEntity<Object> handleValidationException(ConstraintViolationException e) {

		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + 
					violation.getPropertyPath() + ": " + violation.getMessage());
		}
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ApiException apiException = new ApiException(
				"Errores en validacion",
				HttpStatus.BAD_REQUEST, 
				LocalDateTime.now(), 
				errors);
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(apiException, status);

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public ResponseEntity<Object> handleBadRequest(Exception e) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ApiException apiException = new ApiException(
				e.getMessage(),
				HttpStatus.BAD_REQUEST, 
				LocalDateTime.now(), null);
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(apiException, status);
	}
	

}
