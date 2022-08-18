package com.example.demo.model.service;

import com.example.demo.model.entity.enumeration.RoleName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleServiceModel extends BaseServiceModel {
    private RoleName name;
}
