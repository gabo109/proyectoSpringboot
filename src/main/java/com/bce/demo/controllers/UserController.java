package com.bce.demo.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bce.demo.dto.UserDto;
import com.bce.demo.entities.User;
import com.bce.demo.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users); // 200 OK
    }

    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.create(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED); // 201 Created
    }

    @PostMapping("/add-users")
    public ResponseEntity<List<User>> createUsers(@RequestBody List<User> users) {
        List<User> createdUsers = userService.createUsers(users);
        return new ResponseEntity<>(createdUsers, HttpStatus.CREATED); // 201 Created
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUserById(user);
        return ResponseEntity.ok(updatedUser); // 200 OK
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Integer id) {
        ModelMapper modelMapper = new ModelMapper();
        User userDb = userService.getUserById(id);

        if (userDb == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }

        UserDto userDto = modelMapper.map(userDb, UserDto.class);
        return ResponseEntity.ok(userDto); // 200 OK
    }
}
