package com.ibm.aac.gerardo.exchange.service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	
	@Autowired
	private RestTemplate template;
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	//@Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
	@CircuitBreaker(name = "default", fallbackMethod = "hardCodedResponse")
	@RateLimiter(name = "default")
	public String sampleApi() {
		logger.info("Sample api call received");
		
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/some-dummy-path", String.class);
		return response.getBody();
	}
	
	public String hardCodedResponse(Exception ex) {
		return "Fallback response";
	}
	
}
