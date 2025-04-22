package com.selflearning.journal.journal_backend.controller;

import com.selflearning.journal.journal_backend.entity.User;
import com.selflearning.journal.journal_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        userService.saveEntry(user);
        return user;
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@PathVariable String userName, @RequestBody User user){
        User userInDb = userService.findByUserName(userName);
        if (userInDb!=null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
