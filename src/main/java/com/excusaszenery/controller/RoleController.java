package com.excusaszenery.controller;

import com.excusaszenery.model.Role;
import com.excusaszenery.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*") // Para permitir pruebas desde Postman/Angular
public class RoleController {
    @Autowired
    private  RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> create(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.createRole(role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        boolean deleted = roleService.deleteRole(id);
        return deleted ? ResponseEntity.ok("Rol eliminado") :
                ResponseEntity.badRequest().body("Rol no encontrado");
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAll(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getOne(@PathVariable Integer id) {
        return roleService.getRoleById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
