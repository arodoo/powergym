package com.utcv.powergym.powergym.web;

import com.utcv.powergym.powergym.dao.UserDao;
import com.utcv.powergym.powergym.dto.UserDTO;
import com.utcv.powergym.powergym.entity.User;
import com.utcv.powergym.powergym.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserRestController {

    private UserService userService;

    private UserDao userDao;

    public UserRestController(UserService userService, UserDao userDao) {
        this.userService = userService;
        this.userDao = userDao;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/find")
    public UserDTO getUser(@RequestParam(name = "email", defaultValue = "") String email) {
        return userService.loadUserDTOByEmail(email);
    }

    @PostMapping
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        User user = userService.loadUserByEmail(userDTO.getEmail());
        if (user != null) throw new RuntimeException("Email already exists");
        return userService.createUser(userDTO);
    }

    @PutMapping("/{userId}")
    public UserDTO updateUser(@RequestBody UserDTO userDTO, @PathVariable Long userId) {
        userDTO.setUserId(userId);
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userDao.findById(userId).orElseThrow(() -> new EntityNotFoundException("User with id" + userId + " not found"));
        userService.removeUser(userId);
    }

}
