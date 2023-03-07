package com.backend.currencyexchange.service;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import com.backend.currencyexchange.controller.CurrencyExchangeController;
import com.backend.currencyexchange.data.CurrencyConvertorResDTO;
import com.backend.currencyexchange.data.CurrencyExchangeResDTO;
import com.backend.currencyexchange.utils.LatestAPIResults;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class CurrencyConvertorServiceImpl implements CurrencyConvertorService{
	
	private static Logger logger = LoggerFactory.getLogger(CurrencyConvertorServiceImpl.class.getName());

	@Override
	public CurrencyConvertorResDTO getCurrencyConvert(String from, String to, String amount) throws IOException {
		logger.info("Inside CurrencyConvertorServiceImpl.getCurrencyConvert: "+from + to+ amount);
		
		CurrencyConvertorResDTO responseObject = new CurrencyConvertorResDTO();
		Double maxRate = LatestAPIResults.getMaxMinRate(1);
		Double minRate = LatestAPIResults.getMaxMinRate(0);
		responseObject.setMaxValue(maxRate * Double.parseDouble(amount));
		responseObject.setMinValue(minRate * Double.parseDouble(amount));
		return responseObject;
	}

}
