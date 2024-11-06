package com.bss;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BssEsimApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BssEsimApisApplication.class, args);
	}
	
//	@Value("${server.http.port}")
//	private int httpPort;
//
//	@Bean
//	public EmbeddedServletContainerCustomizer customizeTomcatConnector() {
//
//		return new EmbeddedServletContainerCustomizer() {
//
//			@Override
//			public void customize(ConfigurableEmbeddedServletContainer container) {
//
//				if (container instanceof TomcatEmbeddedServletContainerFactory) {
//					TomcatEmbeddedServletContainerFactory containerFactory =
//					(TomcatEmbeddedServletContainerFactory) container;
//					Connector connector = new Connector(TomcatEmbeddedServletContainerFactory.DEFAULT_PROTOCOL);
//					connector.setPort(httpPort);
//					containerFactory.addAdditionalTomcatConnectors(connector);
//				}
//			}
//		};
//	}
	
//	@Bean
//	 public RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//	      TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
//	 
//	      SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
//	                      .loadTrustMaterial(null, acceptingTrustStrategy)
//	                      .build();
//	 
//	      SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
//	 
//	      CloseableHttpClient httpClient = HttpClients.custom()
//	                      .setSSLSocketFactory(csf)
//	                      .build();
//	 
//	      HttpComponentsClientHttpRequestFactory requestFactory =
//	                      new HttpComponentsClientHttpRequestFactory();
//	 
//	      requestFactory.setHttpClient(httpClient);
//	      RestTemplate restTemplate = new RestTemplate(requestFactory);
//	     return restTemplate;
//	  }

}
