package com.backend.currencyexchange.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.currencyexchange.data.CurrencyConvertorResDTO;
import com.backend.currencyexchange.service.CurrencyConvertorService;

@CrossOrigin
@RestController
@RequestMapping("/api/service/currency-convertor")
public class CurrencyConvertController {
	
	@Autowired
	CurrencyConvertorService currencyConvertorService;
	
	private static Logger logger = LoggerFactory.getLogger(CurrencyConvertController.class.getName());

	@GetMapping(value= {"/convert"})
	public @ResponseBody CurrencyConvertorResDTO getCurrencyConvertor(@RequestParam String from,@RequestParam String to,@RequestParam String amount) throws IOException {
		logger.info("Inside CurrencyConvertController.getCurrencyConvertor: ");
		return currencyConvertorService.getCurrencyConvert(from, to, amount);
	}
	
}
