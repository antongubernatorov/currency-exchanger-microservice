package com.gubernatorov.exchangeprocessingservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ACCOUNT")
public class AccountEntity {

    @Id
    @SequenceGenerator(name = "account_generator", sequenceName = "ACCOUNT_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "account_generator", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "CURRENCY_CODE", length = 3, nullable = false)
    private String currencyCode;

    @Column(name = "BALANCE", length = 3, nullable = false)
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
