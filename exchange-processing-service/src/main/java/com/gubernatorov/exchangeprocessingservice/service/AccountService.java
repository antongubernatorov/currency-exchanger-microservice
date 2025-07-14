package com.gubernatorov.exchangeprocessingservice.service;

import com.gubernatorov.exchangeprocessingservice.dto.NewAccountDTO;
import com.gubernatorov.exchangeprocessingservice.model.AccountEntity;
import com.gubernatorov.exchangeprocessingservice.model.AccountEvent;
import com.gubernatorov.exchangeprocessingservice.model.Operation;
import com.gubernatorov.exchangeprocessingservice.repository.AccountRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final ApplicationEventPublisher eventPublisher;

    public AccountService(AccountRepository accountRepository, ApplicationEventPublisher eventPublisher) {
        this.accountRepository = accountRepository;
        this.eventPublisher = eventPublisher;
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

            eventPublisher.publishEvent(createEvent(uid, acc, targetAccount, operation, money));

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

    private AccountEvent createEvent(String uid, AccountEntity acc, Long targetId, Operation operation, BigDecimal amount) {
        var current = new Date();
        return AccountEvent.builder()
                .uuid(uid)
                .accountId(acc.getId())
                .currency(acc.getCurrencyCode())
                .operation(operation)
                .fromAccount(targetId)
                .amount(amount)
                .userId(acc.getUserId())
                .created(current)
                .build();
    }
}
