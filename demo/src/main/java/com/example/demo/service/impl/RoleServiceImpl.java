package com.example.demo.service.impl;

import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.RoleNameEnum;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void initRoles() {
        if (roleRepository.count() == 0) {
            roleRepository.saveAll(Arrays.stream(RoleNameEnum.values()).map(Role::new).toList());


        }

    }

    @Override
    public Role findRole(RoleNameEnum roleNameEnum) {
        return roleRepository.findByName(roleNameEnum).orElse(null);

    }
}
