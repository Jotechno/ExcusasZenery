package com.excusaszenery.service;

import com.excusaszenery.exception.ResourceNotFoundException;
import com.excusaszenery.exception.ResourceConflictException;
import com.excusaszenery.model.Role;
import com.excusaszenery.model.User;
import com.excusaszenery.repository.RoleRepository;
import com.excusaszenery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.excusaszenery.dto.UserRequestDto;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    //encriopador para las contraseñas
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(UserRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ResourceConflictException("El email ya está en uso.");
        } else if (userRepository.existsByUsername(dto.getUsername())) {
            throw new ResourceConflictException("El nombre de usuario ya está en uso.");
        }

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setStatus(dto.getStatus() != null ? dto.getStatus() : true);
        user.setRole(role);

        return userRepository.save(user);
    }


    @Override
    public User updateUser(Integer id, UserRequestDto dto) {
        return userRepository.findById(id).map(user -> {
            if (dto.getUsername() != null) user.setUsername(dto.getUsername());
            if (dto.getEmail() != null) user.setEmail(dto.getEmail());
            if (dto.getPassword() != null) user.setPassword(dto.getPassword());
            if (dto.getStatus() != null) user.setStatus(dto.getStatus());
            if (dto.getRoleId() != null) {
                Role role = roleRepository.findById(dto.getRoleId())
                        .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
                user.setRole(role);
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
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
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
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
