package com.bss.util;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



public abstract class CoreResponseHandler implements Serializable {


	protected HttpStatus httpCode;

	protected ResponseStatusEnum status;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3790980019865748284L;
	//public final static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * @return Gives current timestamp
	 */	
	public  String getTimeStamp() {
		return sdf.format(new Date());
		//return LocalDateTime.now().format(formatter);
	}

	/**
	 * @return http status code from HttpStatus
	 */
	public abstract int getHttpCode();
	/**
	 * @return http status code from HttpStatus
	 */
	public abstract String getStatus();
}
