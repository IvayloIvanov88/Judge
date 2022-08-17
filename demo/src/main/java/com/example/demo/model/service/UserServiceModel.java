package com.example.demo.model.service;

import com.example.demo.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceModel {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String git;
    private Role role;

}
