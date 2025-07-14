package com.gubernatorov.exchangeprocessingservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gubernatorov.exchangeprocessingservice.model.AccountEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AccountEventSendingService {

    public static final String ACCOUNT_EVENTS = "account-events";
    private final ObjectMapper mapper = new ObjectMapper();
    private final KafkaTemplate<Long, String> kafkaTemplate;

    public AccountEventSendingService(KafkaTemplate<Long, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(AccountEvent event) {
        var accountId = event.getAccountId();
        String message;
        try {
            message = mapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        var future = kafkaTemplate.send(ACCOUNT_EVENTS, accountId, message);
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
