package com.example.springbootfcmpushnotification.repository;

import com.example.springbootfcmpushnotification.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
