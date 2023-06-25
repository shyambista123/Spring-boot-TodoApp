package com.shyam.todo.service;

import com.shyam.todo.model.UserDtls;
import org.springframework.stereotype.Service;

public interface UserService {
    public UserDtls createUser(UserDtls user);
    public boolean checkEmail(String email);
}
