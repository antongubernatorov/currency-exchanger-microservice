package com.gubernatorov.historyservice.controller

import com.gubernatorov.historyservice.model.AccountEvent
import com.gubernatorov.historyservice.service.AccountEventService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("history")
class AccountHistoryController(private val historyService: AccountEventService) {

    @GetMapping("/account/{id}")
    fun retrieveAccountHistory(@PathVariable("id") accountId: Long): List<AccountEvent> =
        historyService.findAllByAccountEvents(accountId, 1)
}