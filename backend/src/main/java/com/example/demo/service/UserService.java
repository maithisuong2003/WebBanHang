package com.example.demo.service;
import com.example.demo.constant.PredefinedRole;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
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
        return userMapper.mapToUserResponse(userEntity);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        userEntities.removeIf(userEntity -> userEntity.getUserName().equalsIgnoreCase("amdin"));
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
        userEntity.setUserName(userRequest.getUserName());
        userEntity.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findByName(PredefinedRole.USER_ROLE)
                .orElseThrow(() -> new ApplicationContextException("")))));
        userRepository.save(userEntity);
        userMapper.mapToUserResponse(userEntity);
        return null;
    }
    @Override
    public UserResponse updateUser(Integer id, UserRequest userRequest) {
        return null;
    }

    @Override
    public void deleteUser(Integer id) {

    }

}
