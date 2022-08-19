package com.example.demo.service.impl;

import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enumeration.RoleName;
import com.example.demo.model.service.UserServiceModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.CurrentUser;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import judgev2.data.view.UserProfileViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(RoleService roleService, UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserServiceModel userServiceModel) {
        userServiceModel.setRole(roleService.findByName(userRepository.count() == 0 ? RoleName.ADMIN : RoleName.USER));

        UserEntity userEntity = modelMapper
                .map(userServiceModel, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public void login(UserServiceModel userServiceModel) {
        Optional<UserEntity> optUser =
                userRepository.findByUsername(userServiceModel.getUsername());

        UserEntity user = optUser.get();
        currentUser.setUsername(user.getUsername())
                .setId(userServiceModel.getId())
                .setRole(userServiceModel.getRole().getName());
    }

    @Override
    public void loginUser(String userName) {
        UserEntity user = userRepository.findByUsername(userName).orElseThrow();

        currentUser.
                setId(user.getId()).
                setUsername(user.getUsername()).
                setRole(user.getRole().getName());
    }

    @Override
    public UserServiceModel findByUsername(UserServiceModel userServiceModel) {
        UserEntity userEntity = userRepository.findByUsername(userServiceModel.getUsername()).orElse(null);

        return userEntity == null ? null : modelMapper.map(userEntity, UserServiceModel.class);
    }

    @Override
    public void logout() {
        currentUser.clear();
    }

    @Override
    public boolean authenticate(String userName, String password) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(userName);

        if (userEntityOptional.isEmpty()) {
            return false;
        } else {
            return passwordEncoder.matches(password, userEntityOptional.get().getPassword());
        }
    }

    @Override
    public List<String> findAllUsernames() {
        return userRepository.findAllUsernames();
    }

    @Override
    public void changeRole(String username, RoleName roleNameEnum) {
        Optional<UserEntity> user = userRepository.findByUsername(username);

        if (user.isPresent() && user.get().getRole().getName() != roleNameEnum) {
            user.get().setRole(modelMapper.map(roleService.findByName(roleNameEnum), RoleEntity.class));
            userRepository.save(user.get());
        }
    }

    @Override
    public UserEntity findById(String id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public UserProfileViewModel findProfileById(String id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        UserProfileViewModel userProfileViewModel = modelMapper
                .map(userEntity, UserProfileViewModel.class);

        userProfileViewModel.setHomework(userEntity
                .getHomework()
                .stream()
                .map(h -> h.getExerciseEntity().getName())
                .collect(Collectors.toSet()));

        return userProfileViewModel;
    }

    @Override
    public Integer findUserCount() {
        return userRepository.findUserCount();
    }
}
