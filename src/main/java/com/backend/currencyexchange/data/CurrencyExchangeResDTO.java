package com.backend.currencyexchange.data;

import java.util.Date;

public class CurrencyExchangeResDTO {

	private Double exchangeRate;
	private String source;
	private Date date;
	
	
	public Double getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

}
