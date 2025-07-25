package com.gubernatorov.historyservice.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import java.io.Serializable
import java.math.BigDecimal
import java.util.Date

@Entity
@IdClass(EventKey::class)
data class AccountEvent(

    @Id
    @Column(name="uid", nullable = false)
    val uuid: String,

    @Id
    @Column(name="account_id", nullable = false)
    val accountId: Long,

    @Column(name="user_id", nullable = false)
    val userId: Long,

    @Column(name="from_account", nullable = true)
    val fromAccount: Long?,

    @Column(name="currency_code", length = 3, nullable = false)
    val currency: String,

    @Column(name="operation_code", nullable = false)
    val operation: Operation,

    @Column(name="amount", nullable = false)
    val amount: BigDecimal,

    @Column(name="created", nullable = false)
    val created: Date
)

@Embeddable
class EventKey(
    val accountId: Long,
    val uuid: String
): Serializable