package com.gubernatorov.exchangeprocessingservice.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

public class NewAccountDTO {

    @JsonAlias("currency")
    private String currencyCode;

    @JsonAlias("user")
    private Long userId;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
