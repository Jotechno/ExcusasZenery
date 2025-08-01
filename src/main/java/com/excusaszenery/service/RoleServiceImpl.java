package com.excusaszenery.service;

import com.excusaszenery.exception.ResourceNotFoundException;
import com.excusaszenery.model.Role;
import com.excusaszenery.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.excusaszenery.exception.ResourceConflictException;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        if (roleRepository.existsByroleName(role.getRoleName())) {
            throw new ResourceConflictException("El rol ya existe.");
        }else{
            return roleRepository.save(role);
        }
    }

   @Override
    public Role updateRole(Integer id, Role role) {
        return null;
    }

    @Override
    public boolean deleteRole(Integer id) {
        if (roleRepository.existsById(id)){
            roleRepository.deleteById(id);
            return true;
        }
        throw new ResourceNotFoundException("No existe rol con ese id.");
    }

    @Override
    public Optional<Role> getRoleById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
