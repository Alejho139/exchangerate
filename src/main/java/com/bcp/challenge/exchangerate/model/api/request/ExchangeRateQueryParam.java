package com.bcp.challenge.exchangerate.model.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRateQueryParam {

    private double amount;
    private String originCurrency;
    private String destinationCurrency;
    private Float exchangeRate;
}
