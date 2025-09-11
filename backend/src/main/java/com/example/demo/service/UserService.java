package com.example.demo.service;
import com.example.demo.constant.PredefinedRole;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.UserMapper;
import com.example.demo.reponsitories.RoleRepository;
import com.example.demo.reponsitories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                .orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
        if (!userEntity.getIsActive()) {
            throw new AppException(ErrorCode.USER_INACTIVE);
        }
        return userMapper.mapToUserResponse(userEntity);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        userEntities.removeIf(userEntity ->!userEntity.getIsActive() || userEntity.getUserName().equalsIgnoreCase("admin"));
        return userEntities.stream().map(userMapper::mapToUserResponse).toList();
    }
    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if(userRepository.findByEmail(userRequest.getEmail()).isPresent()){
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        if(userRepository.findByUserName(userRequest.getUserName()).isPresent()){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        UserEntity userEntity = userMapper.mapToUserEntity(userRequest);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userEntity.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findByName(PredefinedRole.USER_ROLE)
                .orElseThrow(() -> new ApplicationContextException("")))));
        userEntity.setIsActive(true);
        userRepository.save(userEntity);
        return userMapper.mapToUserResponse(userEntity);
    }
    @Override
    public UserResponse updateUser(Integer id, UserRequest userRequest) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
        if (!userEntity.getIsActive()) {
            throw new AppException(ErrorCode.USER_INACTIVE);
        }
        userRepository.findByEmail(userRequest.getEmail())
                .filter(user -> !user.getId().equals(id))
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.EMAIL_EXISTED);
                });
        userRepository.findByUserName(userRequest.getUserName())
                .filter(user -> !user.getId().equals(id))
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USER_EXISTED);
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
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (!userEntity.getIsActive()) {
            throw new AppException(ErrorCode.USER_ALREADY_INACTIVE);
        }
        userEntity.setIsActive(false);
        userRepository.save(userEntity);
    }

}
