package com.bcp.challenge.exchangerate.model.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRateResponse {

    private double amount;
    private double amountExchange;
    private String originCurrency;
    private String destinationCurrency;
    private Float exchangeRate;

}
