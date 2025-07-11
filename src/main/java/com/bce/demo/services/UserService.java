package com.bce.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bce.demo.dao.UserRepository;
import com.bce.demo.entities.User;

@Service
public class UserService {
     @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public List<User> createUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    public User updateUserById(User user) {
        User usuario = getUserById(user.getId());
        if (usuario != null) {
            usuario.setAddress(user.getAddress());
            usuario.setName(user.getName());
            usuario.setPhone(user.getPhone());
            return userRepository.save(user);
        } else {
            return new User();
        }
    }

    public String deleteUserById(Integer id) {
        userRepository.deleteById(id);
        return "User Deleted";
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
}
