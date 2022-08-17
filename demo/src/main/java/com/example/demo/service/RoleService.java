package com.example.demo.service;

import com.example.demo.model.entity.enumeration.RoleName;
import com.example.demo.model.service.RoleServiceModel;

public interface RoleService {
    void initRoles();
    RoleServiceModel findByName(RoleName name);
}
