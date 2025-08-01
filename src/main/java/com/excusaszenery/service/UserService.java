package com.excusaszenery.service;

import com.excusaszenery.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    User updateUser(Integer id, User user);
    boolean deleteUser(Integer id);
    Optional<User> getUserById(Integer id);
    List<User> getAllUsers();
    boolean toggleUserStatus(Integer id);
}
