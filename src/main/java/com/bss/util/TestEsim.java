package com.bss.util;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.*;
import java.util.stream.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.function.*;
public class TestEsim {

	public static void main(String[] args) throws Exception{
		//String certificatePath = "C:\\esim_neotel.crt";
		String certificatePath = "/home/wpitsadmin/esim/esim_neotel.crt";
	      
	       Certificate certificate = loadCertificate(certificatePath);
	 
	       // Create an SSL context
	       SSLContext sslContext = SSLContext.getInstance("TLS");
	       sslContext.init(null, getTrustManagers(certificate), null);
	       
	       
	       // Make the API call
	       //makeApiCall(sslContext);
		
	       JSONObject json = new JSONObject();
	       JSONParser jsonParser = new JSONParser();
	       json = (JSONObject)jsonParser.parse("{\r\n"
	       		+ "    \"header\": {\r\n"
	       		+ "        \"functionRequesterIdentifier\": \"l . l\",\r\n"
	       		+ "        \"functionCallIdentifier\": \"TX-567\"\r\n"
	       		+ "    },\r\n"
	       		+ "    \"eid\": \"89033024AAAAAAAAAAAAAAAAAAAAAAAA\",\r\n"
	       		+ "    \"iccid\": \"89445385599850071520\"\r\n"
	       		+ "}");
	       
	       
	       
	       URL url = new URL("https://dppoapiproxy.linksfield.net/apiProxy/es2/downloadOrder");
	       HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	       conn.setSSLSocketFactory(sslContext.getSocketFactory());
	       conn.setRequestMethod("POST");
	       conn.setRequestProperty("Content-Type", "application/json");
	       conn.setRequestProperty("Accept", "application/json");
	       conn.setDoOutput(true);
	       try(OutputStream os = conn.getOutputStream()) {
	    	    byte[] input = json.toJSONString().getBytes("utf-8");
	    	    os.write(input, 0, input.length);			
	    	}
	       StringBuilder response  = null;
	       try(BufferedReader br = new BufferedReader(
	    		   new InputStreamReader(conn.getInputStream(), "utf-8"))) {
	    		     response = new StringBuilder();
	    		     String responseLine = null;
	    		     while ((responseLine = br.readLine()) != null) {
	    		         response.append(responseLine.trim());
	    		     }
	    		     System.out.println(response.toString());
	    		 }

	}
	
	private static Certificate loadCertificate(String certificatePath) throws Exception {
	       CertificateFactory cf = CertificateFactory.getInstance("X.509");
	       FileInputStream is = new FileInputStream(certificatePath);
	       Certificate cert = cf.generateCertificate(is);
	       is.close();
	       return cert;
	   }
	 
	   private static TrustManager[] getTrustManagers(Certificate certificate) throws Exception {
	       KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
	       keyStore.load(null, null);
	       keyStore.setCertificateEntry("certificate", certificate);
	 
	       TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
	       tmf.init(keyStore);
	       return tmf.getTrustManagers();
	       //retu
	   }

}
