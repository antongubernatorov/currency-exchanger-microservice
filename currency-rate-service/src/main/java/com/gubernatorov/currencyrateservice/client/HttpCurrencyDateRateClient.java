package com.gubernatorov.currencyrateservice.client;

import java.time.LocalDate;

public interface HttpCurrencyDateRateClient {
    String requestByDate(LocalDate date);
}
