package com.example.springbootfcmpushnotification.repository;

import com.example.springbootfcmpushnotification.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Topic findTopicByTopicId(String topicId);
    List<Topic> findTopicByUser_Id(Long userId);
}
