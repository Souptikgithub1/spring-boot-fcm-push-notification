package com.example.springbootfcmpushnotification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.springbootfcmpushnotification.entity")
@EnableJpaRepositories("com.example.springbootfcmpushnotification.repository")
public class SpringBootFcmPushNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFcmPushNotificationApplication.class, args);
	}

}
