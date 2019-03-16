package com.example.springbootfcmpushnotification.service;

import com.example.springbootfcmpushnotification.entity.Topic;
import com.example.springbootfcmpushnotification.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Boolean isTopicExist(String topicId) {
        Topic topic = this.topicRepository.findTopicByTopicId(topicId);
        return topic!=null;
    }

    @Override
    public Topic add(Topic topic) {
        Topic existingTopic = this.topicRepository.findTopicByTopicId(topic.getTopicId());
        if(existingTopic == null) {
            return this.topicRepository.save(topic);
        }
        return existingTopic;
    }
}
