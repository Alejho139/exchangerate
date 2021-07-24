package com.bcp.challenge.exchangerate.expose.web;

import com.bcp.challenge.exchangerate.model.api.request.ExchangeRateQueryParam;
import com.bcp.challenge.exchangerate.model.api.response.ExchangeRateResponse;
import com.bcp.challenge.exchangerate.service.ExchangeRateService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/exchange-rate")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping("/calculate")
    public Single<ExchangeRateResponse> calculate(ExchangeRateQueryParam param) {
        return exchangeRateService.calculate(param)
                .subscribeOn(Schedulers.io());
    }

    @PutMapping("/update")
    public Single<String> update(ExchangeRateQueryParam param) {
        return exchangeRateService.updateExchangeRate(param)
                .subscribeOn(Schedulers.io());
    }

    @PutMapping("/updateAll")
    public Single<String> updateAll(@RequestBody List<ExchangeRateQueryParam> param) {
        return exchangeRateService.updateExchangeRateList(param)
                .subscribeOn(Schedulers.io());
    }





}
