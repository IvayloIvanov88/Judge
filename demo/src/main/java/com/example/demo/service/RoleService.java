package com.example.demo.service;

import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.RoleNameEnum;

public interface RoleService {

    void initRoles();

    Role findRole(RoleNameEnum roleNameEnum);
}
