package com.excusaszenery.service;

import com.excusaszenery.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role createRole(Role role);
    Role updateRole(Integer id, Role role);
    boolean deleteRole(Integer id);
    Optional<Role> getRoleById(Integer id);
    List<Role> getAllRoles();
}
