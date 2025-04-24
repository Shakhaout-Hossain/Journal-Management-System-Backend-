package com.selflearning.journal.journal_backend.service;

import com.selflearning.journal.journal_backend.entity.JournalEntry;
import com.selflearning.journal.journal_backend.entity.User;
import com.selflearning.journal.journal_backend.repository.JournalEntryRepository;
import com.selflearning.journal.journal_backend.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        userRepository.save(user);
    }
    public List<User> getAll(){

        return userRepository.findAll();
    }
    public User getById(ObjectId myId){

        return userRepository.findById(myId).orElse(null);
    }

    public void deleteById(ObjectId myId)
    {
        userRepository.deleteById(myId);
    }
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
