package com.gubernatorov.exchangeprocessingservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class CurrencyService {

    private final RestTemplate restClient;

    @Value("${service.currency.url}")
    private String currencyUrl;

    public CurrencyService(RestTemplate restClient) {
        this.restClient = restClient;
    }

    public BigDecimal loadCurrencyRate(String code) {
        return restClient.getForObject(currencyUrl + "/currency/rate/{code}", BigDecimal.class, code);
    }
}
