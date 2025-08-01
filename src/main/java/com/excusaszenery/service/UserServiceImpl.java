package com.excusaszenery.service;

import com.excusaszenery.exception.ResourceNotFoundException;
import com.excusaszenery.model.User;
import com.excusaszenery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.excusaszenery.exception.ResourceConflictException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResourceConflictException("El email ya está en uso.");
        } else if (userRepository.existsByUsername(user.getUsername())) {
            throw new ResourceConflictException("El nombre de usuario ya está en uso.");
        } else {
            return userRepository.save(user);
        }
    }


    @Override
    public User updateUser(Integer id, User newData) {
        return userRepository.findById(id).map(user -> {
            if (newData.getUsername() != null)
                user.setUsername(newData.getUsername());

            if (newData.getEmail() != null)
                user.setEmail(newData.getEmail());

            if (newData.getPassword() != null)
                user.setPassword(newData.getPassword());

            if (newData.getRole() != null)
                user.setRole(newData.getRole());

            if (newData.getStatus() != null)
                user.setStatus(newData.getStatus());

            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public boolean deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        throw new ResourceNotFoundException("No existe usuario con ese id.");
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean toggleUserStatus(Integer id) {
        return userRepository.findById(id).map(user -> {
            user.setStatus(!user.getStatus());
            userRepository.save(user);
            return true;
        }).orElse(false);
    }

}
