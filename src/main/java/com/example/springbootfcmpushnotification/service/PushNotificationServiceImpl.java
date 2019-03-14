package com.example.springbootfcmpushnotification.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class PushNotificationServiceImpl implements PushNotificationService {

    private static final String FIREBASE_SERVER_KEY = "AAAA8UZ6L3E:APA91bFu5Igq5MbB6qxA9R_4JWguQqbATfs0NgbIjSs8vhv0a8sG7GsIrsMSopGEKv9hhtajzcbkhaSSC3L5ZokC18EmwEMLdK8-0mTT3Lf_AqtknBSfybq202wXYjwiUBbGPUjaNrm8";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    @Async
    @Override
    public CompletableFuture<String> send(HttpEntity<String> entity) {
        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }
}
