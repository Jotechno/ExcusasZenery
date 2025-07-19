package com.excusaszenery.service;

import com.excusaszenery.model.Role;
import com.excusaszenery.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    // Metodos importados directamente desde JPARepository
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }
    public Optional<Role> getRoleById(Long id){
        return roleRepository.findById(id);
    }

    //Metodo creado por mi para encontrar el rol por nombre

    public Optional<Role> getRoleByName(String role){
        return roleRepository.findByRole(role) ;
    }
}
