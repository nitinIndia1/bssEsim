package com.bss.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


//@ControllerAdvice
public class CustomExceptionHandler {

	
	//@ResponseStatus(HttpStatus.BAD_REQUEST)
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CoreResponseHandler> handleInvalidArgument(MethodArgumentNotValidException ex) {
		long l_diff = 0;
		long l_time_start = System.currentTimeMillis();	
		System.out.println("here here here here here");
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->{
			errors.put(error.getField(), error.getDefaultMessage());
		});
		long l_end_time = System.currentTimeMillis();
		l_diff = l_end_time-l_time_start;
		//return errors;
		return new ResponseEntity<CoreResponseHandler>(new SuccessResponseBeanRefined(HttpStatus.BAD_REQUEST,
				ResponseStatusEnum.FAILED, ApplicationResponse.Failed, errors, l_diff + " ms"),
				HttpStatus.BAD_REQUEST);
	}
	
}
