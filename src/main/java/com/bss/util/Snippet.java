package com.bss.util;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

public class Snippet{
	private HttpURLConnection httpConn;
	private Map<String, Object> queryParams;


	public void httpPostForm() throws IOException {
		this.queryParams = new HashMap<>();
		this.queryParams.put("image", encodeToString());
		URL url = new URL("http://182.74.113.62:9003/ekyc/validate");
		httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setUseCaches(false);
		httpConn.setDoOutput(true);    // indicates POST method
		httpConn.setDoInput(true);
		httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		String response = "";
		byte[] postDataBytes = this.getParamsByte(queryParams);
		httpConn.getOutputStream().write(postDataBytes);
		// Check the http status
		int status = httpConn.getResponseCode();
		if (status == HttpURLConnection.HTTP_OK) {
			ByteArrayOutputStream result = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = httpConn.getInputStream().read(buffer)) != -1) {
				result.write(buffer, 0, length);
			}
			response = result.toString("utf-8");
			httpConn.disconnect();
		} else {
			throw new IOException("Server returned non-OK status: " + status);
		}
		System.out.println(response);
	}

	private byte[] getParamsByte(Map<String, Object> params) {
		byte[] result = null;
		StringBuilder postData = new StringBuilder();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (postData.length() != 0) {
				postData.append('&');
			}
			postData.append(this.encodeParam(param.getKey()));
			postData.append('=');
			postData.append(this.encodeParam(String.valueOf(param.getValue())));
		}
		try {
			result = postData.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	private String encodeParam(String data) {
		String result = "";
		try {
			result = URLEncoder.encode(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String encodeToString() {
		String base64="";
		try{
			//
			String path="C:\\Users\\nitin\\OneDrive\\Desktop\\rough\\one\\272364074\\self.jpg";
			InputStream iSteamReader = new FileInputStream(path);
			byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
			base64 = Base64.getEncoder().encodeToString(imageBytes);
			//System.out.println(base64);
		}catch(Exception e){
			e.printStackTrace();
		}
		return base64;
	}



	public static void main(String a[]) {
		Snippet snippet = new Snippet();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					snippet.httpPostForm();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t1.start();
	}

	
}