package com.example.demo.service;
import com.example.demo.constant.PredefinedRole;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.reponsitories.RoleRepository;
import com.example.demo.reponsitories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    @Override
    public UserResponse findUserById(Integer id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not exits"));
        if (!userEntity.getIsActive()) {
            throw new RuntimeException("User is inactive");
        }
        return userMapper.mapToUserResponse(userEntity);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        userEntities.removeIf(userEntity ->!userEntity.getIsActive() || userEntity.getUserName().equalsIgnoreCase("amdin"));
        return userEntities.stream().map(userMapper::mapToUserResponse).toList();
    }
    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if(userRepository.findByEmail(userRequest.getEmail()).isPresent()){
            throw new ApplicationContextException("Email da ton tai");
        }
        if(userRepository.findByUserName(userRequest.getUserName()).isPresent()){
            throw new ApplicationContextException("UserName da ton tai");
        }
        UserEntity userEntity = userMapper.mapToUserEntity(userRequest);
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userEntity.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findByName(PredefinedRole.USER_ROLE)
                .orElseThrow(() -> new ApplicationContextException("")))));
        userEntity.setIsActive(true);
        userRepository.save(userEntity);
        userMapper.mapToUserResponse(userEntity);
        return null;
    }
    @Override
    public UserResponse updateUser(Integer id, UserRequest userRequest) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not exist"));
        if (!userEntity.getIsActive()) {
            throw new RuntimeException("User is inactive");
        }
        userRepository.findByEmail(userRequest.getEmail())
                .filter(user -> !user.getId().equals(id))
                .ifPresent(user -> {
                    throw new ApplicationContextException("Email đã tồn tại!");
                });
        userRepository.findByUserName(userRequest.getUserName())
                .filter(user -> !user.getId().equals(id))
                .ifPresent(user -> {
                    throw new ApplicationContextException("User nay da ton tai");
                });
        userMapper.updateUserEntityFromRequest(userRequest,userEntity);
        if (userRequest.getRoleNames() != null && !userRequest.getRoleNames().isEmpty()) {
            Set<RoleEntity> newRoles = userRequest.getRoleNames().stream()
                    .map(roleName -> roleRepository.findByName(roleName)
                            .orElseThrow(() -> new ApplicationContextException("Role không tồn tại: " + roleName)))
                    .collect(Collectors.toSet());
            userEntity.setRoles(newRoles);
        }
        userRepository.save(userEntity);
        return userMapper.mapToUserResponse(userEntity);
    }

    @Override
    public void deleteUser(Integer id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not exist"));
        if (!userEntity.getIsActive()) {
            throw new RuntimeException("User is already inactive");
        }
        userEntity.setIsActive(false);
        userRepository.save(userEntity);
    }

}
