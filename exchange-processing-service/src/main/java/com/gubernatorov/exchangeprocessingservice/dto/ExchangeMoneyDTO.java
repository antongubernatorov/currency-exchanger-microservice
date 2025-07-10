package com.gubernatorov.exchangeprocessingservice.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.math.BigDecimal;

public class ExchangeMoneyDTO {

    @JsonAlias("uid")
    private String exchangeUid;

    @JsonAlias("from")
    private Long fromAccountId;

    @JsonAlias("to")
    private Long toAccountId;

    @JsonAlias("amount")
    private BigDecimal money;

    public String getExchangeUid() {
        return exchangeUid;
    }

    public void setExchangeUid(String exchangeUid) {
        this.exchangeUid = exchangeUid;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
