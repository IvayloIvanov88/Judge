package com.example.demo.service;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enumeration.RoleName;
import com.example.demo.model.service.UserServiceModel;
import judgev2.data.view.UserProfileViewModel;

import java.util.List;

public interface UserService {
    void register(UserServiceModel userServiceModel);
    void login(UserServiceModel userServiceModel);
    UserServiceModel findByUsernameAndPassword(UserServiceModel userServiceModel);

    void logout();

    List<String> findAllUsernames();

    void changeRole(String username, RoleName valueOf);

    UserEntity findById(String id);

    UserProfileViewModel findProfileById(String id);

    Integer findUserCount();
}
