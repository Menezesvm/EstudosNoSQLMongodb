package com.menezesvm.estudosmongodb.controller.exceptionhandler;

import com.menezesvm.estudosmongodb.controller.standarerror.StandarError;
import com.menezesvm.estudosmongodb.service.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandarError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandarError error = new StandarError(System.currentTimeMillis(), status.value(), "Não encontrao", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
}
