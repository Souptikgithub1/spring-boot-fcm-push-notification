package com.example.springbootfcmpushnotification.service;

import org.springframework.http.HttpEntity;

import java.util.concurrent.CompletableFuture;

public interface PushNotificationService {
    CompletableFuture<String> send(HttpEntity<String> entity);
}
