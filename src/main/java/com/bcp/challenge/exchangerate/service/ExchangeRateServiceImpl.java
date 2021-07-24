package com.bcp.challenge.exchangerate.service;

import com.bcp.challenge.exchangerate.model.api.request.ExchangeRateQueryParam;
import com.bcp.challenge.exchangerate.model.api.response.ExchangeRateResponse;
import com.bcp.challenge.exchangerate.model.entity.Currency;
import com.bcp.challenge.exchangerate.repository.CurrencyRepository;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public Single<ExchangeRateResponse> calculate(ExchangeRateQueryParam exchangeRateQueryParam) {

        return currencyRepository.findExchangeRate(exchangeRateQueryParam.getOriginCurrency(),
                exchangeRateQueryParam.getDestinationCurrency())
                .map(aFloat -> Single.just(aFloat).map(exchangeRate -> {
                    BigDecimal amountExchange = convertCurrency(exchangeRate,
                            exchangeRateQueryParam.getAmount());

                    ExchangeRateResponse response = new ExchangeRateResponse();

                    response.setAmount(exchangeRateQueryParam.getAmount());
                    response.setAmountExchange(amountExchange.doubleValue());
                    response.setOriginCurrency(exchangeRateQueryParam.getOriginCurrency());
                    response.setDestinationCurrency(exchangeRateQueryParam.getDestinationCurrency());
                    response.setExchangeRate(exchangeRate);
                    return response;
                })).orElseGet(() -> Single.error(new EntityNotFoundException()));
    }

    @Override
    public Single<String> updateExchangeRate(ExchangeRateQueryParam exchangeRateQueryParam) {

        return update(exchangeRateQueryParam);
    }

    private Single<String> update(ExchangeRateQueryParam exchangeRateQueryParam) {

        return currencyRepository.findExchangeRate(exchangeRateQueryParam.getOriginCurrency(),
                exchangeRateQueryParam.getDestinationCurrency())
                .map(aFloat -> Single.just(aFloat).map(exchangeRate -> {
                    int result = currencyRepository.updateExchangeRate(
                            exchangeRateQueryParam.getExchangeRate(),
                            exchangeRateQueryParam.getOriginCurrency(),
                            exchangeRateQueryParam.getDestinationCurrency());

                    if (result == 1) {
                        return "Guardado con Exito";
                    }

                    throw new RuntimeException();
                }).onErrorResumeNext(Single::error))
                .orElseGet(() -> Single.error(new EntityNotFoundException("No se guardo correctamente")));
    }



    private BigDecimal convertCurrency(Float exchangeRate, double amount) {
        return new BigDecimal(exchangeRate * amount).setScale(3, RoundingMode.HALF_UP);
    }

    @Override
    public Single<String> updateExchangeRateList(List<ExchangeRateQueryParam> list) {

        list.forEach(param -> {

            Optional<Float> exchangeRate = currencyRepository
                    .findExchangeRate(param.getOriginCurrency(), param.getDestinationCurrency());

            if (exchangeRate.isPresent()) {
                currencyRepository.updateExchangeRate(param.getExchangeRate(),
                        param.getOriginCurrency(), param.getDestinationCurrency());
            } else {
                currencyRepository.insertExchangeRate(param.getOriginCurrency(),
                        param.getDestinationCurrency(), param.getExchangeRate());
            }

        });

        return Single.just("Guardado con Exito");
    }



}
