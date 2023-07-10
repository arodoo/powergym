package com.utcv.powergym.powergym.service.impl;

import com.utcv.powergym.powergym.dao.RoleDao;
import com.utcv.powergym.powergym.dao.UserDao;
import com.utcv.powergym.powergym.entity.Role;
import com.utcv.powergym.powergym.entity.User;
import com.utcv.powergym.powergym.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public User loadUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User createUser(String userName, String email, String password) {
        User user = new User(userName, email, password);
        userDao.save(user);
        return user;
    }


    @Override
    public void assignRoleToUser(String email, String roleName) {
        User user = loadUserByEmail(email);
        Role role = roleDao.findByRoleName(roleName);
        user.assignRoleToUser(role);
    }

    @Override
    public List<User> getAllUsers(){
        return userDao.findAll();
    }
}
