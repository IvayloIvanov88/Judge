package com.example.demo.service;

import com.example.demo.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void registerUser(UserServiceModel userServiceModel);

}
