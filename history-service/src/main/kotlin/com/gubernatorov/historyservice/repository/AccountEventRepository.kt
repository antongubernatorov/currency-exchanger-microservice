package com.gubernatorov.historyservice.repository

import com.gubernatorov.historyservice.model.AccountEvent
import com.gubernatorov.historyservice.model.EventKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountEventRepository: JpaRepository<AccountEvent, EventKey> {

    fun findAllByAccountIdAndUserIdOrderByCreatedDesc(accountId: Long, userId: Long): List<AccountEvent>
}