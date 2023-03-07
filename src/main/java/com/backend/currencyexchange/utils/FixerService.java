package com.backend.currencyexchange.utils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Date;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.backend.currencyexchange.data.CurrencyExchangeResDTO;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class FixerService {
	
	@Value("${api.fixer.secret}")
	private String secret;
	private String BASE_URL = "https://api.apilayer.com/fixer/latest";
	public String source = "https://fixer.io";
	
	private static Logger logger = LoggerFactory.getLogger(FixerService.class.getName());
	
	public CurrencyExchangeResDTO getCurrencyExchangeRate(String from, String to){
		logger.info("Inside FixerService.getCurrencyExchangeRate: "+ from + to);

		CurrencyExchangeResDTO responseObject = new CurrencyExchangeResDTO();
		
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		HttpUrl.Builder urlBuilder 
	      = HttpUrl.parse(BASE_URL).newBuilder();
	    urlBuilder.addQueryParameter("symbols", to).addQueryParameter("base",from);

	    String url = urlBuilder.build().toString();
		
	    Request request = new Request.Builder()
	      .url(url)
	      .addHeader("apikey",secret)
	      .build();

	    Response response;
		try {
			response = client.newCall(request).execute();
		    if(response.isSuccessful()) {
		    	JSONObject jsonResponse = new JSONObject(response.body().string());
                Date date=new Date(jsonResponse.getInt("timestamp"));  
                Double exRate = jsonResponse.getJSONObject("rates").getDouble(to);
		    	responseObject.setExchangeRate(exRate);
		    	responseObject.setDate(date);
		    }else {
				logger.error("Inside FixerService.getCurrencyExchangeRate: API CALL Failed");
		    }
		}catch(SocketTimeoutException e) {
			logger.error("SocketTimeoutException Inside FixerService.getCurrencyExchangeRate: ");
			e.printStackTrace();
		} 
		catch (IOException e) {
			logger.error("IOEXception Inside FixerService.getCurrencyExchangeRate: ");
			e.printStackTrace();
		}
	    responseObject.setSource(this.source);
	    return responseObject;
	    
	    
	}
	
}
