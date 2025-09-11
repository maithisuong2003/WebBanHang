package com.example.demo.service;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.dto.request.RoleRequest;
import com.example.demo.dto.response.RoleResponse;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.reponsitories.PermissionRepository;
import com.example.demo.reponsitories.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse create(RoleRequest request) {
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }
    public RoleResponse deletePermission(String name, String permissionName) {
        var role = roleRepository.findById(name).orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXITED));
        var permission = permissionRepository.findById(permissionName).orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_EXITED));
        role.getPermissions().remove(permission);
        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }
    public RoleResponse addPermission(String name, PermissionRequest permissionRequest) {
        var role = roleRepository.findById(name).orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXITED));
        var permission = permissionRepository.findById(permissionRequest.getName()).orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_EXITED));
        role.getPermissions().add(permission);
        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}