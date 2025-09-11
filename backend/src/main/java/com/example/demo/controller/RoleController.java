package com.example.demo.controller;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.dto.request.RoleRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.RoleResponse;
import com.example.demo.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleService roleService;

    @PostMapping
    @PreAuthorize("hasAuthority('USER_POST')")
    ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .message("Permission created!")
                .result(roleService.create(request))
                .build();
    }

    @PostMapping("/{name}/permissions")
    @PreAuthorize("hasAuthority('USER_POST')")
    ApiResponse<RoleResponse> addPermission(@PathVariable String name, @RequestBody PermissionRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .message("Permission added!")
                .result(roleService.addPermission(name, request))
                .build();
    }
    @GetMapping
    @PreAuthorize("hasAuthority('USER_GET')")
    ApiResponse<List<RoleResponse>> getAllRole() {
        return ApiResponse.<List<RoleResponse>>builder()
                .message("All permissions!")
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{name}")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    ApiResponse<Void> deleteRole(@PathVariable String name) {
        roleService.delete(name);
        return ApiResponse.<Void>builder()
                .message("Permission deleted!")
                .build();
    }

    @DeleteMapping("/{name}/permissions/{permissionName}")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    ApiResponse<RoleResponse> deletePermission(@PathVariable String name, @PathVariable String permissionName) {
        return ApiResponse.<RoleResponse>builder()
                .message("Permission deleted!")
                .result(roleService.deletePermission(name, permissionName))
                .build();
    }

}