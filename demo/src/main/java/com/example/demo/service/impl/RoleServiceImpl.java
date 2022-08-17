package com.example.demo.service.impl;

import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.entity.enumeration.RoleName;
import com.example.demo.model.service.RoleServiceModel;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void initRoles() {
        if (roleRepository.count() == 0) {
            Arrays.stream(RoleName.values())
                    .forEach(r -> roleRepository.save(new RoleEntity(r)));
        }
    }

    @Override
    public RoleServiceModel findByName(RoleName name) {
        return modelMapper.map(roleRepository.findByName(name), RoleServiceModel.class);
    }
}
