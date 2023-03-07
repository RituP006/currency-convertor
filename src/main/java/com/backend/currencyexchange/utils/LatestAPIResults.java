package com.backend.currencyexchange.utils;

import java.util.ArrayList;

import com.backend.currencyexchange.data.CurrencyExchangeResDTO;

public class LatestAPIResults {
	
	public static ArrayList<CurrencyExchangeResDTO> allRates = new ArrayList<CurrencyExchangeResDTO>();
	
	public static void addResult(CurrencyExchangeResDTO resDTO) {
		if(allRates.size() == 3) {
			allRates.clear();
		}
		allRates.add(resDTO);
    }
	
	public static ArrayList<CurrencyExchangeResDTO> getLatestResults() {
		return allRates;
	}
	
	public static Double getMaxMinRate(int indicate) {
	
		allRates.sort((o1, o2)
                -> o1.getExchangeRate().compareTo(
                        o2.getExchangeRate()));
		if(indicate == 0) {
			return allRates.get(0).getExchangeRate();
		}else {
			return allRates.get(2).getExchangeRate();
		}
	}
	
}
