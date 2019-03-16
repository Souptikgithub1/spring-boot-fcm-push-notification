package com.example.springbootfcmpushnotification;

import com.example.springbootfcmpushnotification.bean.UserBean;

public class TopicBean {
    private Long id;
    private UserBean user;
    private String topicId;

    public TopicBean() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
}
