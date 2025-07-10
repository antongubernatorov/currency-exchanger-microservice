package com.gubernatorov.exchangeprocessingservice.repository;

import com.gubernatorov.exchangeprocessingservice.model.AccountEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

    List<AccountEntity> findAllByUserId(Long userId);
}
