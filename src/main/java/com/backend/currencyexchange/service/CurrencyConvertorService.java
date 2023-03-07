package com.backend.currencyexchange.service;

import java.io.IOException;

import com.backend.currencyexchange.data.CurrencyConvertorResDTO;

public interface CurrencyConvertorService {
	
	CurrencyConvertorResDTO getCurrencyConvert(String from, String to, String amount) throws IOException;

}
