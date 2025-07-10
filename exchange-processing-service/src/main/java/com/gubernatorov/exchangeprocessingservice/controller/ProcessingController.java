package com.gubernatorov.exchangeprocessingservice.controller;

import com.gubernatorov.exchangeprocessingservice.dto.ExchangeMoneyDTO;
import com.gubernatorov.exchangeprocessingservice.dto.NewAccountDTO;
import com.gubernatorov.exchangeprocessingservice.dto.PutAccountMoneyDTO;
import com.gubernatorov.exchangeprocessingservice.model.AccountEntity;
import com.gubernatorov.exchangeprocessingservice.model.Operation;
import com.gubernatorov.exchangeprocessingservice.service.AccountService;
import com.gubernatorov.exchangeprocessingservice.service.ExchangeService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("processing")
public class ProcessingController {

    private final AccountService accountService;

    private final ExchangeService exchangeService;

    public ProcessingController(AccountService accountService, ExchangeService exchangeService) {
        this.accountService = accountService;
        this.exchangeService = exchangeService;
    }

    @PostMapping("/account")
    public AccountEntity createAccount(@RequestBody NewAccountDTO account) {
        return accountService.createNewAccount(account);
    }

    @PutMapping("/account/{id}")
    public AccountEntity putMoney(@PathVariable("id") Long accountId, @RequestBody PutAccountMoneyDTO data) {
        return accountService.addMoneyToAccount(data.getUid(), accountId, null, Operation.PUT, data.getMoney());
    }

    @PutMapping("/exchange/{uid}")
    public BigDecimal exchange(@PathVariable("uid") String uid, @RequestBody ExchangeMoneyDTO dto) {
        return exchangeService.exchangeCurrency(uid, dto.getFromAccountId(), dto.getToAccountId(), dto.getMoney());
    }

    @GetMapping("/accounts/{user}")
    public List<AccountEntity> getAllAccount(@PathVariable("user") Long user) {
        return accountService.getAllAccount(user);
    }
}
