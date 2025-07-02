package com.gubernatorov.exchangeprocessingservice.service;

import com.gubernatorov.exchangeprocessingservice.dto.NewAccountDTO;
import com.gubernatorov.exchangeprocessingservice.model.AccountEntity;
import com.gubernatorov.exchangeprocessingservice.model.Operation;
import com.gubernatorov.exchangeprocessingservice.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    public final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountEntity createNewAccount(NewAccountDTO dto) {
        var account = new AccountEntity();
        account.setCurrencyCode(dto.getCurrencyCode());
        account.setUserId(dto.getUserId());
        account.setBalance(new BigDecimal(0));

        return accountRepository.save(account);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public AccountEntity addMoneyToAccount(String uid, Long accountId, Long targetAccount, Operation operation, BigDecimal money) {
        Optional<AccountEntity> account = accountRepository.findById(accountId);
        var result = account.map(acc -> {
            var balance = acc.getBalance().add(money);
            acc.setBalance(balance);

            return accountRepository.save(acc);
        }).orElseThrow(() -> new IllegalArgumentException("Account with ID " + accountId + " not found"));
        return result;
    }

    public AccountEntity getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Account with ID: " + id + " not found"));
    }

    public List<AccountEntity> getAllAccount(Long userId) {
        Optional<AccountEntity> accounts = accountRepository.findById(userId);
        if (accounts.isEmpty()) {
            return new ArrayList<>();
        } else {
            return accounts.stream().toList();
        }
    }
}
