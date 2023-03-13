package com.backend.currencyexchange.service;

import java.io.IOException;
import java.util.ArrayList;

import com.backend.currencyexchange.data.CurrencyExchangeResDTO;

public interface CurrencyExchangeService {

	ArrayList<CurrencyExchangeResDTO> getCurrencyExchangeRate(String from, String to);

}
