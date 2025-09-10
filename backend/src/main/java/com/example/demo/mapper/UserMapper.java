package com.example.demo.mapper;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", source = "roles")
    UserResponse mapToUserResponse(UserEntity userEntity);
    UserEntity mapToUserEntity(UserRequest userRequest);
    default Set<String> mapRoles(Set<RoleEntity> roles) {
        if (roles == null) return null;
        return roles.stream()
                .map(RoleEntity::getName)
                .collect(Collectors.toSet());
    }
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserEntityFromRequest(UserRequest userRequest, @MappingTarget UserEntity userEntity);
}