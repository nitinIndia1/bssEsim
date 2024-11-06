package com.bss.util;


import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(content=Include.NON_NULL)
public class ValidationErrorBean extends CoreResponseHandler {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -4595611533025176944L;
	/**
	 * variables for Response
	 */
	private Errors errors;
	
	/**
	 * @param httpCode
	 * @param status
	 * @param errors
	 */
	public ValidationErrorBean(HttpStatus httpCode,ResponseStatusEnum status,Errors errors) {
		this.httpCode=httpCode;
		this.status=status;
		this.errors=errors;
	}
	
	/**
	 * @return This method return list of validation
	 *          errors of type Error Source
	 */
	public List<ErrorSource> getErrors() {
		List<ErrorSource> errorSources=new ArrayList<ValidationErrorBean.ErrorSource>();
		List<FieldError> list = this.errors.getFieldErrors();
		
		for(FieldError err:list) {
			ErrorSource errorSource=new ErrorSource(err.getField(),err.getDefaultMessage());
			errorSources.add(errorSource);
		}
		
		/*this.errors.getFieldErrors()
		.stream()
		.forEach(err->{
			ErrorSource errorSource=new ErrorSource(err.getField(),err.getDefaultMessage());
			errorSources.add(errorSource);
		});*/
		return errorSources;
	}
	
	
	
	/**
	 * This Class is used as a bean for validation error
	 *
	 */
	private class ErrorSource{
		 
		 private String field;
		 private String cause;
		 
		 /**
		 * @param field  Name of the field which causes error 
		 * @param cause  Cause for Error
		 */
		public ErrorSource(String field,String cause) {
			this.cause=cause;
			this.field=field;
		 }

		/**
		 * @return Gives field name which cause error
		 */
		@SuppressWarnings("unused")
		public String getField() {
			return field;
		}

		/**
		 * @return Gives the reason for Error
		 */
		@SuppressWarnings("unused")
		public String getCause() {
			return cause;
		}
		 
	 }

	@Override
	public int getHttpCode() {
		return this.httpCode.value();
	}

	@Override
	public String getStatus() {
		return this.status.name();
	}
	
	
	 
 }