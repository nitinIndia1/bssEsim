package com.bss.controller;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.time.Duration;
import java.util.*;
import java.util.stream.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.bss.service.EsimService;

import java.util.function.*;

@RestController
public class NewController {
	@Autowired
	private EsimService service;
	@PostMapping(value="downloadOrder")
	public ResponseEntity<?> apiDownloadOrder(@RequestBody JSONObject json) throws Exception{
	       
	       String response = service.callAPI(json, "POST", "https://dppoapiproxy.linksfield.net/apiProxy/es2/downloadOrder");
	       return new ResponseEntity<>(response, HttpStatus.OK);
	       //return null;
		
//		RestTemplate restTemplate = new RestTemplate();
//		
//		restTemplate = new RestTemplateBuilder(null).
//				setConnectTimeout(Duration.ofMinutes(3)).build();
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//		//headers.add("Authorization", "Bearer "+accessToken);
//		ResponseEntity<String> response = null;
//		HttpEntity formEntity = new HttpEntity(json, headers);
//		try {
//			String url = "https://dppoapiproxy.linksfield.net/apiProxy/es2/downloadOrder";
//			System.out.println(url);
//			response = restTemplate.exchange(url , HttpMethod.POST,
//					formEntity, String.class);
//
//			if(response!=null && response.getStatusCode().is2xxSuccessful()) {
//				String actualResponse = response.getBody();
//				JSONParser parser =new JSONParser();
//				JSONObject obj=null;
//				try {
//					obj = (JSONObject) parser.parse(actualResponse);
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//				return new ResponseEntity<>(obj, HttpStatus.OK);
//			}
//			else if(response!=null && !response.getStatusCode().is2xxSuccessful()){
//				System.out.println("1");
//				return new ResponseEntity<>(response, response.getStatusCode());
//			}
//			else
//			{
//				System.out.println("2");
//				return new ResponseEntity<>("ERROR", response.getStatusCode());
//
//			}
//		}
//		//			catch(HttpClientErrorException ex) {
//		//			ex.printStackTrace();
//		//			String lMsg = ex.getLocalizedMessage();
//		//			//String msg = ex.getMessage();
//		////			JSONParser parser =new JSONParser();
//		////			JSONObject obj = null;
//		////			try {
//		////			obj = (JSONObject)parser.parse(lMsg);
//		////		}catch(Exception ex_) {
//		////			ex_.printStackTrace();
//		////		}
//		//			System.out.println("exception check check check check check ");
//		//			
//		//			long l_end_time = System.currentTimeMillis();
//		//			long l_diff = l_end_time-l_time_start;
//		//			return	new ResponseEntity<CoreResponseHandler>(new SuccessResponseBeanRefined(response.getStatusCode(), ResponseStatusEnum.FAILED, ApplicationResponse.Failed,lMsg,l_diff+" ms"),response.getStatusCode());				
//		//
//		//		}
//
//
//		catch(HttpClientErrorException ex) {
//			System.out.println("herehherehehrhehehrehrherhe");
//			ex.printStackTrace();
//			String msg = ex.getResponseBodyAsString();
//
//			JSONParser parser =new JSONParser();
//			JSONObject obj = null;
//			try {
//				obj = (JSONObject)parser.parse(msg);
//
//			}catch(Exception ex_) {
//				ex_.printStackTrace();
//				return new ResponseEntity<>(obj+" error", HttpStatus.INTERNAL_SERVER_ERROR);
//
//			}
//			return new ResponseEntity<>(obj+" error", ex.getStatusCode());
//
//		}
	}
	
//	private static Certificate loadCertificate(String certificatePath) throws Exception {
//	       CertificateFactory cf = CertificateFactory.getInstance("X.509");
//	       FileInputStream is = new FileInputStream(certificatePath);
//	       Certificate cert = cf.generateCertificate(is);
//	       is.close();
//	       return cert;
//	   }
//	 
//	   private static TrustManager[] getTrustManagers(Certificate certificate) throws Exception {
//	       KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//	       keyStore.load(null, null);
//	       keyStore.setCertificateEntry("certificate", certificate);
//	 
//	       TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//	       tmf.init(keyStore);
//	       return tmf.getTrustManagers();
//	       //return tmf;
//	   }
	
	@PostMapping(value="confirmOrder")
	public ResponseEntity<?> apiconfirmOrder(@RequestBody JSONObject json) throws Exception{
	       
	       String response = service.callAPI(json, "POST", "https://ddppoapiproxy.linksfield.net/apiProxy/es2/confirmOrder");
	       return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PostMapping(value="releaseProfile")
	public ResponseEntity<?> apireleaseProfile(@RequestBody JSONObject json) throws Exception{
	       
	       String response = service.callAPI(json, "POST", "https://ddppoapiproxy.linksfield.net/apiProxy/es2/releaseProfile");
	       return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PostMapping(value="profileStatus")
	public ResponseEntity<?> apiprofileStatus(@RequestBody JSONObject json) throws Exception{
	       
	       String response = service.callAPI(json, "GET", "https://dppoapiproxy.linksfield.net/gsma/rsp2/es2plus/V1/ProfileStatus");
	       return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PostMapping(value="handleDownloadProgressInfo")
	public ResponseEntity<?> apihandledownloadProgressInfo(@RequestBody JSONObject json) throws Exception{
	       
	       String response = service.callAPI(json, "POST", "https://dppoapiproxy.linksfield.net/gsma/rsp2/es2plus/handleDownloadProgressInfo");
	       return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PostMapping(value="operPgpFile")
	public ResponseEntity<?> apioperPgpFile(@RequestBody JSONObject json) throws Exception{
	       
	       String response = service.callAPI(json, "POST", "https://dppoapiproxy.linksfield.net/apiProxy/operPgpFile");
	       return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PostMapping(value="deployAndProduceQr")
	public ResponseEntity<?> apideployAndProduceQr(@RequestBody JSONObject json) throws Exception{
	       
	       String response = service.callAPI(json, "POST", "https://dppoapiproxy.linksfield.net/apiProxy/deployAndProduceQr");
	       return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PostMapping(value="cancelOrder")
	public ResponseEntity<?> apicancelOrder(@RequestBody JSONObject json) throws Exception{
	       
	       String response = service.callAPI(json, "POST", "https://dppoapiproxy.linksfield.net/apiProxy/es2/cancelOrder");
	       return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	
	
}
