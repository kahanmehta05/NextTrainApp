package com.kahan.service;

import com.kahan.entity.User;

public interface UserService {
    User getUserByUsername(String username); // ✅ added method
}
