package com.backend.currencyexchange.utils;

import java.util.ArrayList;
import java.util.List;

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
	
	public static void setLatestResults(List<CurrencyExchangeResDTO> result) {
		allRates.clear();
		allRates = (ArrayList<CurrencyExchangeResDTO>) result;
	}
	
	public static Double getMaxMinRate(int indicate) {
		
		if(allRates.size() > 0) { 
			if(allRates.size() >=1) {
				allRates.sort((o1, o2)
		                -> o1.getExchangeRate().compareTo(
		                        o2.getExchangeRate()));
				if(indicate == 0) {
					return allRates.get(0).getExchangeRate();
				}else {
					return allRates.get(2).getExchangeRate();
				}
			}else {
				return allRates.get(0).getExchangeRate();
			}
			
		}else {
			return 0.0;
		}
		
	}
	
}
