package com.ibm.aac.gerardo.exchange.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.aac.gerardo.exchange.service.dtos.CurrencyExchange;

@Repository
public interface CurrencyExchangeRepository 
extends JpaRepository<CurrencyExchange, Long> {
	
	CurrencyExchange findByFromAndTo(String from, String to);
	
}
