package com.excusaszenery.service;

import com.excusaszenery.model.User;
import com.excusaszenery.dto.UserRequestDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(UserRequestDto dto);
    User updateUser(Integer id, UserRequestDto dto);
    boolean deleteUser(Integer id);
    Optional<User> getUserById(Integer id);
    List<User> getAllUsers();
    boolean toggleUserStatus(Integer id);
}
