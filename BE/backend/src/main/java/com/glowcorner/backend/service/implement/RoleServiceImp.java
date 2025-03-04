package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.model.DTO.RoleDTO;
import com.glowcorner.backend.model.mapper.RoleMapper;
import com.glowcorner.backend.repository.RoleRepository;
import com.glowcorner.backend.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roleMapper.toRoleDTO(roles);
    }


}
