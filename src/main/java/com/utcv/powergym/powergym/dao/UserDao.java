package com.utcv.powergym.powergym.dao;

import com.utcv.powergym.powergym.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
