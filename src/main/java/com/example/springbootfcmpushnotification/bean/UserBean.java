package com.example.springbootfcmpushnotification.bean;

import com.example.springbootfcmpushnotification.TopicBean;

import java.util.List;

public class UserBean {
    private Long id;
    private String name;
    private String email;
    private String photoUrl;
    private List<TopicBean> topics;

    public UserBean() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<TopicBean> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicBean> topics) {
        this.topics = topics;
    }
}
