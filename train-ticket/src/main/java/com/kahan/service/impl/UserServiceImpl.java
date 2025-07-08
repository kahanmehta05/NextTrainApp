package com.kahan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kahan.entity.User;
import com.kahan.repository.UserRepository;
import com.kahan.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
return userRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("User not found: " + username));

    }
}
