package com.bss.util;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(content=Include.NON_NULL)
public class SuccessResponseBean extends CoreResponseHandler {


	private static final long serialVersionUID = -4286290688834610090L;
	/**
	 * variables for response message
	 */
	private String message;
	private HttpStatus httpcode;
	private ResponseStatusEnum status;
	
	/**
	 * @param httpCode
	 * @param status
	 * @param message
	 */
	public SuccessResponseBean(HttpStatus httpCode,ResponseStatusEnum status,String message) {
		this.httpcode=httpCode;
		this.status=status;
		this.message=message;
	}
	
	
	/**
	 * @return This method returns success response
	 */
	public String getMessage() {
		return message.toString();
	}



	@Override
	public int getHttpCode() {
		return this.httpcode.value();
	}


	@Override
	public String getStatus() {
		return this.status.name();
	}
	
	
	
}
