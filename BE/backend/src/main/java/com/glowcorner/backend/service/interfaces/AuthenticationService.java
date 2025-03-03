package com.glowcorner.backend.service.interfaces;

public interface AuthenticationService {
    boolean login(String username, String password);
}
