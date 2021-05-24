package com.ntscorp.reservation.exception;

import javax.validation.ConstraintViolationException;

import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.ntscorp.reservation.exception.custom.FileLoadException;
import com.ntscorp.reservation.exception.custom.NotFoundException;
import com.ntscorp.reservation.exception.status.ErrorStatus;

@ControllerAdvice("com.ntscorp.reservation.viewcontroller")
public class ControllerAdvicer {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler({MethodArgumentTypeMismatchException.class, ConstraintViolationException.class,
		MethodArgumentNotValidException.class, BindingException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private String handleInValidInputValueException(Exception exception) {
		logger.error(ErrorStatus.INVALID_INPUT_VALUE.getMessage() + " has occured!", exception);
		return "error/400";
	}

	@ExceptionHandler({EmptyResultDataAccessException.class, NotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private String handleNotFoundException(Exception exception) {
		logger.error(ErrorStatus.INVALID_INPUT_VALUE.getMessage() + " has occured!", exception);
		return "error/404";
	}
	
	@ExceptionHandler({FileLoadException.class, DataAccessException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleInternalServerErrorException(Exception exception) {
		logger.error(ErrorStatus.INTERNAL_SERVER_ERROR.getMessage() + " has occured!", exception);
		return "error/500";
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	private String handleAllException(Exception exception) {
		logger.error(ErrorStatus.INVALID_INPUT_VALUE.getMessage() + " has occured!", exception);
		return "error/500";
	}
}
