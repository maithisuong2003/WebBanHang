package com.example.demo.mapper;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.dto.response.PermissionResponse;
import com.example.demo.entity.PermissionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface PermissionMapper {
    PermissionEntity toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(PermissionEntity entity);
}
