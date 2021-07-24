package com.bcp.challenge.exchangerate.service;

import com.bcp.challenge.exchangerate.model.api.request.ExchangeRateQueryParam;
import com.bcp.challenge.exchangerate.model.api.response.ExchangeRateResponse;
import com.bcp.challenge.exchangerate.model.entity.Currency;
import io.reactivex.Single;

import java.util.List;

public interface ExchangeRateService {

    Single<ExchangeRateResponse> calculate(ExchangeRateQueryParam exchangeRateQueryParam);

    Single<String> updateExchangeRate(ExchangeRateQueryParam exchangeRateQueryParam);

    Single<String> updateExchangeRateList(List<ExchangeRateQueryParam> exchangeRateQueryParam);
}
