package com.excusaszenery.controller;

import com.excusaszenery.model.User;
import com.excusaszenery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Para permitir pruebas desde Postman/Angular
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        boolean deleted = userService.deleteUser(id);
        return deleted ? ResponseEntity.ok("Usuario eliminado") :
                ResponseEntity.badRequest().body("Usuario no encontrado");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable Integer id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PatchMapping("/{id}/toggle-status")
    public ResponseEntity<String> toggleStatus(@PathVariable Integer id) {
        boolean updated = userService.toggleUserStatus(id);
        return updated ? ResponseEntity.ok("Estado cambiado") :
                ResponseEntity.badRequest().body("Usuario no encontrado");
    }
}
