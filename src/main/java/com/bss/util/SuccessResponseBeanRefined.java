package com.bss.util;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(content=Include.NON_NULL)
public class SuccessResponseBeanRefined extends SuccessResponseBean implements Serializable{

	private Object data = null;
	private String latencyTime;
	
	public SuccessResponseBeanRefined(HttpStatus httpCode, ResponseStatusEnum status, String message,Object data,String latencyTime) {
		super(httpCode, status, message);
		this.data=data;
		this.latencyTime=latencyTime;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6787624918232577242L;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getLatencyTime() {
		return latencyTime;
	}

	public void setLatencyTime(String latencyTime) {
		this.latencyTime = latencyTime;
	}


	
	

	
	
	
}
