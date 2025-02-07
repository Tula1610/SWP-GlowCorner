package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImp implements Runnable {
    @Autowired
    private RoleRepository roleRepository;


}
