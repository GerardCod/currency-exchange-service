package com.ibm.aac.gerardo.exchange.service.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;

import com.ibm.aac.gerardo.exchange.service.dtos.CurrencyExchange;
import com.ibm.aac.gerardo.exchange.service.repositories.CurrencyExchangeRepository;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(
				@PathVariable String from,
				@PathVariable String to
			) {
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		
		if (currencyExchange == null) {
			return new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(20.0));
		}
		
		String port = env.getProperty("local.server.port");
		
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
	
}
