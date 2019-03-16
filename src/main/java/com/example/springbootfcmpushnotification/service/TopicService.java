package com.example.springbootfcmpushnotification.service;

import com.example.springbootfcmpushnotification.entity.Topic;

public interface TopicService {
    Boolean isTopicExist(String topicId);
    Topic add(Topic topic);
}
