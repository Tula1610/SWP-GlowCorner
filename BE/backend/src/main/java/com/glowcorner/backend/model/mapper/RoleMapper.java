package com.glowcorner.backend.model.mapper;

import com.glowcorner.backend.model.DTO.RoleDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {

    public RoleDTO toRoleDTO(Role role) {
        if (role == null) {
            return null;
        }

        return RoleDTO.builder()
                .roleID(role.getRoleID().toHexString()) // Convert ObjectId to String
                .roleName(role.getRoleName())
                .roleDescription(role.getRoleDescription())
                .build();
    }

    public List<RoleDTO> toRoleDTO(List<Role> roles) {
        return roles.stream().map(this::toRoleDTO).collect(Collectors.toList());
    }


}
