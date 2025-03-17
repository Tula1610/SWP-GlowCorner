package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.Authentication;
import com.glowcorner.backend.repository.AuthenticationRepository;
import com.glowcorner.backend.service.interfaces.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {

    private final AuthenticationRepository authenticationRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean login(String username, String password) {
        Optional<Authentication> authentication = authenticationRepository.findByUserName(username);
        return authentication.isPresent() && bCryptPasswordEncoder.matches(password, authentication.get().getPasswordHash());
    }

}
