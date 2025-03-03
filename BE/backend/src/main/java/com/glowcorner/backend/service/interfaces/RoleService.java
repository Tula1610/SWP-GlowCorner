package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.model.DTO.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllRoles();
}
