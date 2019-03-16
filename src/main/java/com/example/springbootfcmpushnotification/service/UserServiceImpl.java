package com.example.springbootfcmpushnotification.service;

import com.example.springbootfcmpushnotification.TopicBean;
import com.example.springbootfcmpushnotification.bean.UserBean;
import com.example.springbootfcmpushnotification.entity.Topic;
import com.example.springbootfcmpushnotification.entity.User;
import com.example.springbootfcmpushnotification.repository.TopicRepository;
import com.example.springbootfcmpushnotification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public List<UserBean> getAll() {
        List<User> users = this.userRepository.findAll();
        List<UserBean> userBeans = new LinkedList<>();
        for(User user: users) {
            UserBean userBean = new UserBean();
            userBean.setId(user.getId());
            userBean.setEmail(user.getEmail());
            userBean.setName(user.getName());
            userBean.setPhotoUrl(user.getPhotoUrl());

            List<Topic> topics = this.topicRepository.findTopicByUser_Id(user.getId());
            List<TopicBean> topicBeans = new LinkedList<>();
            for(Topic topic: topics) {
                TopicBean topicBean = new TopicBean();
                topicBean.setId(topic.getId());
                topicBean.setTopicId(topic.getTopicId());
                topicBean.setUser(null);
                topicBeans.add(topicBean);
            }
            userBean.setTopics(topicBeans);
            userBeans.add(userBean);
        }
        return userBeans;
    }
}
