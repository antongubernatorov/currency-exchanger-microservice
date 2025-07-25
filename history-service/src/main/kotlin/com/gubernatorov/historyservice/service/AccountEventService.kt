package com.gubernatorov.historyservice.service

import com.gubernatorov.historyservice.model.AccountEvent
import com.gubernatorov.historyservice.repository.AccountEventRepository
import org.springframework.stereotype.Service

@Service
class AccountEventService(
    private val repository: AccountEventRepository
) {

    fun findAllByAccountEvents(accountId: Long, userId: Long): List<AccountEvent> {
        return repository.findAllByAccountIdAndUserIdOrderByCreatedDesc(accountId, userId)
    }
}