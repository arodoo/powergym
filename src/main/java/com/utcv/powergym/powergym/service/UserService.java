package com.utcv.powergym.powergym.service;

import com.utcv.powergym.powergym.entity.User;

import java.util.List;

public interface UserService {

    User loadUserByEmail(String email);

    User createUser(String userName, String email, String password);

    void assignRoleToUser(String email, String roleName);

    List<User> getAllUsers();
}
