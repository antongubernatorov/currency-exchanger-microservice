package com.gubernatorov.exchangeprocessingservice.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.math.BigDecimal;

public class PutAccountMoneyDTO {

    @JsonAlias("uid")
    private String uid;

    @JsonAlias("account")
    private long accountId;

    @JsonAlias("amount")
    private BigDecimal money;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
