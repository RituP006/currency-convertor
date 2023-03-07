package com.backend.currencyexchange.utils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.sql.Timestamp;
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
public class CurrencyAPIService {
	
	@Value("${api.freeCurrency.secret}")
	private String secret;
	private String BASE_URL = "https://api.freecurrencyapi.com/v1/latest?";
	public String source = "https://api.freecurrencyapi.com";
	
	private static Logger logger = LoggerFactory.getLogger(CurrencyAPIService.class.getName());

	public CurrencyExchangeResDTO getCurrencyExchangeRate(String from, String to){
		logger.info("Inside CurrencyAPIService.getCurrencyExchangeRate: "+ from + to);

		CurrencyExchangeResDTO responseObject = new CurrencyExchangeResDTO();
		
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		HttpUrl.Builder urlBuilder 
	      = HttpUrl.parse(BASE_URL).newBuilder();
	    urlBuilder.addQueryParameter("apikey", secret).addQueryParameter("currencies", to).addQueryParameter("base_currency",from);

	    String url = urlBuilder.build().toString();
		
	    Request request = new Request.Builder()
	      .url(url)
	      .build();

	    Response response;
		try {
			response = client.newCall(request).execute();
		    if(response.isSuccessful()) {
		    	JSONObject jsonResponse = new JSONObject(response.body().string());
		    	Timestamp ts=new Timestamp(System.currentTimeMillis());  
                Date date=new Date(ts.getTime());  
                Double exRate = jsonResponse.getJSONObject("data").getDouble(to);
		    	responseObject.setExchangeRate(exRate);
		    	responseObject.setDate(date);
		    }else {
				logger.error("Inside CurrencyAPIService.getCurrencyExchangeRate: external API Call Failed ");
		    }
		}catch(SocketTimeoutException e) {
			logger.error("SocketTimeoutException Inside CurrencyAPIService.getCurrencyExchangeRate: ");
			e.printStackTrace();
		} 
		catch (IOException e) {
			logger.error("IOEXception Inside CurrencyAPIService.getCurrencyExchangeRate: ");
			e.printStackTrace();
		}
	    responseObject.setSource(this.source);
	    return responseObject;
	    
	    
	}
	
}
