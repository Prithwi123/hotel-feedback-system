package com.cg.user.services;

import java.util.List;

import com.cg.user.entities.User;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUser();

    User getUserById(String id);

}
