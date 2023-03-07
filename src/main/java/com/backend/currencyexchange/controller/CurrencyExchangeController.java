package com.backend.currencyexchange.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.currencyexchange.data.CurrencyExchangeResDTO;
import com.backend.currencyexchange.service.CurrencyExchangeService;

@RestController
@RequestMapping("/api/service/currency-convertor")
public class CurrencyExchangeController {
	
	@Autowired
	CurrencyExchangeService currencyExchangeService;
	
	private static Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class.getName());
	
	@GetMapping(value= {"/exchange"})
	public @ResponseBody ArrayList<CurrencyExchangeResDTO> getCurrencyExchangeRate(@RequestParam String from,@RequestParam String to) throws IOException {
		logger.info("Inside CurrencyExchangeController.getCurrencyExchangeRate: ");
		return currencyExchangeService.getCurrencyExchangeRate(from, to);
	}
	
}
