package com.example.springbootfcmpushnotification.service;

import com.example.springbootfcmpushnotification.entity.Topic;
import com.example.springbootfcmpushnotification.entity.User;
import com.example.springbootfcmpushnotification.repository.TopicRepository;
import com.example.springbootfcmpushnotification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean isTopicExist(String topicId) {
        Topic topic = this.topicRepository.findTopicByTopicId(topicId);
        return topic!=null;
    }

    @Override
    @Transactional
    public Topic add(Topic topic) {
        User user = this.userRepository.findByEmail(topic.getUser().getEmail());
        if(user != null) {
            User user1 = topic.getUser();
            user1.setId(user.getId());
            topic.setUser(user1);
        }
        Topic existingTopic = this.topicRepository.findTopicByTopicId(topic.getTopicId());
        if(existingTopic == null) {
            return this.topicRepository.save(topic);
        }
        return existingTopic;
    }
}
