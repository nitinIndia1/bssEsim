package com.bss.util;

import java.util.List;

import org.springframework.http.HttpStatus;

public class CommonListResponseBean extends CoreResponseHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8481484602615668246L;
	/**
	 * 
	 */
	
	private final List<?> list;
	
	
	
	/**
	 * @param roleList
	 */
	public CommonListResponseBean(List<?> list) {
		this.list = list;
	}

	/**
	 * @return the roleList
	 */
	public List<?> getList() {
		return list;
	}


	@Override
	public int getHttpCode() {
		
		return HttpStatus.OK.value();
	}

	@Override
	public String getStatus() {
		return ResponseStatusEnum.SUCCESSFUL.name();
	}

	

	
	
	
	
}
