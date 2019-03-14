package com.example.springbootfcmpushnotification.controller;

import com.example.springbootfcmpushnotification.service.PushNotificationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class WebController {

    private final String TOPIC = "JavaSampleApproach";

    @Autowired
    PushNotificationService pushNotificationService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> send() throws Exception {
        Map<String, Object> bodyMap = new HashMap<>();

        //bodyMap.put("to", "/topics/" + TOPIC);
        bodyMap.put("to", "fEp6K72mF20:APA91bGnQX20wKA5aGjSzxo8yQalcJw6HsQyKt7M6Mcl3PpwR7-NCvqCnCtVo6HCN4IOInUCX0uuXApuPJh_6OQoN7Io2Y1hGD523I_PiqXOXKujkABysTdR5baU2rh9e2WDTk95UtFm");
        bodyMap.put("priority", "high");

        Map<String, String> notificationMap = new HashMap<>();
        notificationMap.put("title", "Push Notification");
        notificationMap.put("body", "This is my first push Notificaion!");
        bodyMap.put("notification", notificationMap);

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("Key-1", "JSA Data 1");
        dataMap.put("Key-2", "JSA Data 2");
        bodyMap.put("data", dataMap);

        Gson gson = new Gson();
        String bodyJson =  gson.toJson(bodyMap);

        HttpEntity<String> request = new HttpEntity<>(bodyJson.toString());
        CompletableFuture<String> pushNotification = pushNotificationService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            String firebaseResponse = pushNotification.get();
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        } catch(InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>("Push Notification Error!", HttpStatus.BAD_REQUEST);
    }
}
