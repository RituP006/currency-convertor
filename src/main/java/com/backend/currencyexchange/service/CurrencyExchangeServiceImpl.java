package com.backend.currencyexchange.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.currencyexchange.data.CurrencyExchangeResDTO;
import com.backend.currencyexchange.utils.CurrencyAPIService;
import com.backend.currencyexchange.utils.ExchangeRateDataAPIService;
import com.backend.currencyexchange.utils.FixerService;
import com.backend.currencyexchange.utils.LatestAPIResults;


@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService{
	
	private static Logger logger = LoggerFactory.getLogger(CurrencyExchangeServiceImpl.class.getName());
	
	@Autowired
	private FixerService fixerService;
	
	@Autowired
	private CurrencyAPIService currencyAPIService;
	
	@Autowired
	private ExchangeRateDataAPIService exchangeRateDataAPIService;

	@Override
	public ArrayList<CurrencyExchangeResDTO> getCurrencyExchangeRate(String from, String to){
		logger.info("Inside CurrencyExchangeServiceImpl.getCurrencyExchangeRate: "+ from + to);
		
		CompletableFuture<CurrencyExchangeResDTO> service1 = CompletableFuture.supplyAsync(()->fixerService.getCurrencyExchangeRate(from, to));
		CompletableFuture<CurrencyExchangeResDTO> service2 = CompletableFuture.supplyAsync(()->currencyAPIService.getCurrencyExchangeRate(from, to));
		CompletableFuture<CurrencyExchangeResDTO> service3 = CompletableFuture.supplyAsync(()->exchangeRateDataAPIService.getCurrencyExchangeRate(from, to));

		CompletableFuture<Void> completableFuture = CompletableFuture.allOf(service1,service2,service3);
		
		List<CurrencyExchangeResDTO> result = Stream.of(service1,service2,service3).map(CompletableFuture::join).collect(Collectors.toList());
		logger.info("Inside CurrencyExchangeServiceImpl.getCurrencyExchangeRate: Result "+ result);
		LatestAPIResults.setLatestResults(result);
//		CurrencyExchangeResDTO fixerData = fixerService.getCurrencyExchangeRate(from, to);
//		LatestAPIResults.addResult(fixerData);
//		CurrencyExchangeResDTO currencyAPIData = currencyAPIService.getCurrencyExchangeRate(from, to);
//		LatestAPIResults.addResult(currencyAPIData);
//		CurrencyExchangeResDTO exchangeAPIData = exchangeRateDataAPIService.getCurrencyExchangeRate(from, to);
//		LatestAPIResults.addResult(exchangeAPIData);
		return LatestAPIResults.getLatestResults();
	}

}
