package com.bss.util;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;



@JsonInclude(Include.NON_NULL)
public class CoreErrorResponseBean  extends CoreResponseHandler{
	
	private static final long serialVersionUID = -8953185589299845882L;
	
  
	/**
	 * variable for runtime  or custom message
	 */

	private String errorMessage;
	private String errorRemark;
	
	public CoreErrorResponseBean(HttpStatus errorCode,ResponseStatusEnum response,String message) {
		this.httpCode=errorCode;
		this.status=response;
		this.errorMessage=message;
		this.errorRemark=null;
	}
	
	
	
	public CoreErrorResponseBean(HttpStatus httpCode, ResponseStatusEnum status,
			String errorMessage, String errorRemark) {
		this.httpCode = httpCode;
		this.status = status;
		this.errorMessage = errorMessage;
		this.errorRemark = errorRemark;
	}



	/**
	 * @return error message string
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public int getHttpCode() {
		return this.httpCode.value();
	}


	@Override
	public String getStatus() {
		return this.status.name();
	}
	
	
	public String getErrorRemark() {
		return errorRemark;
	}

	
	
	
	

}
