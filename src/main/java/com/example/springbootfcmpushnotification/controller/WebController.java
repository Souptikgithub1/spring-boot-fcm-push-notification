package com.example.springbootfcmpushnotification.controller;

import com.example.springbootfcmpushnotification.entity.Topic;
import com.example.springbootfcmpushnotification.service.PushNotificationService;
import com.example.springbootfcmpushnotification.service.TopicService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@CrossOrigin
@RestController
public class WebController {

    private final String TOPIC = "JavaSampleApproach";

    @Autowired
    PushNotificationService pushNotificationService;

    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/addTopic", method = RequestMethod.POST)
    public ResponseEntity<?> addTopic(@RequestBody Topic topic) {
        return new ResponseEntity<>(this.topicService.add(topic), HttpStatus.OK);
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> send() throws Exception {
        Map<String, Object> bodyMap = new HashMap<>();

        //bodyMap.put("to", "/topics/" + TOPIC);
        //bodyMap.put("to", "fEp6K72mF20:APA91bGnQX20wKA5aGjSzxo8yQalcJw6HsQyKt7M6Mcl3PpwR7-NCvqCnCtVo6HCN4IOInUCX0uuXApuPJh_6OQoN7Io2Y1hGD523I_PiqXOXKujkABysTdR5baU2rh9e2WDTk95UtFm");
        bodyMap.put("to", "fzIXEj8jcRU:APA91bHfLPb6j62f72xiCQ3GaJ66IGiRYOA-VTimxKGU3k5Fqjoq6GaWeJBpyoH9RH8RN_7qwwYuqmS2AbONtF55R07laHBBcKxghZEvx1wgvCgjtkp9pGI3YmdA1_8GWoD08CEuKSNd");
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
