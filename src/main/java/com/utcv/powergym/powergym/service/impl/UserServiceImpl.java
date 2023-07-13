package com.utcv.powergym.powergym.service.impl;

import com.utcv.powergym.powergym.dao.RoleDao;
import com.utcv.powergym.powergym.dao.UserDao;
import com.utcv.powergym.powergym.dto.UserDTO;
import com.utcv.powergym.powergym.entity.Role;
import com.utcv.powergym.powergym.entity.User;
import com.utcv.powergym.powergym.mapper.UserMapper;
import com.utcv.powergym.powergym.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private RoleDao roleDao;

    private UserMapper userMapper;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.userMapper = userMapper;
    }

    @Override
    public User loadUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public UserDTO loadUserDTOByEmail(String email) {
        UserDTO userDTO = new UserDTO();
        User user = userDao.findByEmail(email);
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());

        return userDTO;
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

    @Override
    public List<UserDTO> getAllUsersDTO() {
        return userMapper.fromUsers(userDao.findAll());
    }


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User(userDTO.getUserName(), userDTO.getEmail(), userDTO.getPasswordHash());
        userDao.save(user);
        return userDTO;
    }

    @Override
    public void removeUser(Long userId) {
        userDao.deleteById(userId);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User loadedUser = userDao.findById(userDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        User user = userMapper.fromUserDTO(userDTO);
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUserName());
        user.setPasswordHash(userDTO.getPasswordHash());
        User updatedUser = userDao.save(user);
        return userMapper.fromUser(updatedUser);
    }
}
