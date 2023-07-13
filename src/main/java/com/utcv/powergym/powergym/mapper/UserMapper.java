package com.utcv.powergym.powergym.mapper;

import com.utcv.powergym.powergym.dto.UserDTO;
import com.utcv.powergym.powergym.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper {

    public UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    public User fromUserDTO(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

    public List<UserDTO> fromUsers(List<User> all) {
        List<UserDTO> userDTOS = new java.util.ArrayList<>();
        for (User user : all) {
            userDTOS.add(fromUser(user));
        }
        return userDTOS;
    }
}
