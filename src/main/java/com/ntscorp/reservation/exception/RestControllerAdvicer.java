package com.ntscorp.reservation.exception;

import java.net.BindException;

import javax.validation.ConstraintViolationException;

import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.ntscorp.reservation.exception.custom.FileLoadException;
import com.ntscorp.reservation.exception.custom.NotFoundException;
import com.ntscorp.reservation.exception.status.ErrorStatus;

@RestControllerAdvice({"com.ntscorp.reservation.domain", "com.ntscorp.reservation.common"})
public class RestControllerAdvicer {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler({MethodArgumentTypeMismatchException.class, ConstraintViolationException.class,
		MethodArgumentNotValidException.class, BindingException.class, BindException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleMethodArgumentTypeMismatchException(Exception exception) {
		logger.error(ErrorStatus.INVALID_INPUT_VALUE.getMessage() + " has occured!", exception);
		return exception.getMessage();
	}

	@ExceptionHandler({EmptyResultDataAccessException.class, NotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleEmptyResultDataAccessException(Exception exception) {
		logger.error(ErrorStatus.NOT_FOUND.getMessage() + " has occured!", exception);
		return exception.getMessage();
	}
	
	@ExceptionHandler({FileLoadException.class, DataAccessException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleInternalServerErrorException(Exception exception) {
		logger.error(ErrorStatus.INTERNAL_SERVER_ERROR.getMessage() + " has occured!", exception);
		return exception.getMessage();
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleAllException(Exception exception) {
		logger.error(ErrorStatus.INTERNAL_SERVER_ERROR.getMessage() + " has occured!", exception);
		return exception.getMessage();
	}
}
