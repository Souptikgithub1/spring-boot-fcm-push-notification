package com.example.springbootfcmpushnotification.controller;

import com.example.springbootfcmpushnotification.entity.Topic;
import com.example.springbootfcmpushnotification.service.PushNotificationService;
import com.example.springbootfcmpushnotification.service.TopicService;
import com.example.springbootfcmpushnotification.service.UserService;
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
import java.util.LinkedList;
import java.util.List;
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
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/addTopic", method = RequestMethod.POST)
    public ResponseEntity<?> addTopic(@RequestBody Topic topic) {
        return new ResponseEntity<>(this.topicService.add(topic), HttpStatus.OK);
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> send(@RequestBody List<Map<String, String>> topicBodyList) throws Exception {
        List<String> firebaseResponseList = new LinkedList<>();

        try {
            for(Map<String, String> topicBody: topicBodyList ){
                Map<String, Object> bodyMap = new HashMap<>();

                //bodyMap.put("to", "/topics/" + TOPIC);
                //bodyMap.put("to", "fEp6K72mF20:APA91bGnQX20wKA5aGjSzxo8yQalcJw6HsQyKt7M6Mcl3PpwR7-NCvqCnCtVo6HCN4IOInUCX0uuXApuPJh_6OQoN7Io2Y1hGD523I_PiqXOXKujkABysTdR5baU2rh9e2WDTk95UtFm");
                bodyMap.put("to", topicBody.get("topicId"));
                bodyMap.put("priority", "high");

                Map<String, String> notificationMap = new HashMap<>();
                notificationMap.put("title", topicBody.get("title"));
                notificationMap.put("body", topicBody.get("body"));
                notificationMap.put("image", "https://firebase.google.com/_static/images/firebase/touchicon-180.png");
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
                String firebaseResponse = pushNotification.get();

                firebaseResponseList.add(firebaseResponse);
            }
            return new ResponseEntity<>(firebaseResponseList, HttpStatus.OK);
        } catch(InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>("Push Notification Error!", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(this.userService.getAll(), HttpStatus.OK);
    }
}
