package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.Authentication;
import com.glowcorner.backend.entity.mongoDB.User;
import com.glowcorner.backend.enums.Role;
import com.glowcorner.backend.model.DTO.GoogleLoginDTO;
import com.glowcorner.backend.model.DTO.LoginDTO;
import com.glowcorner.backend.repository.AuthenticationRepository;
import com.glowcorner.backend.repository.UserRepository;
import com.glowcorner.backend.service.interfaces.AuthenticationService;
import com.glowcorner.backend.utils.JwtUtilHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {

    private final AuthenticationRepository authenticationRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtilHelper jwtUtilHelper;
    @Autowired
    private CounterServiceImpl counterServiceImpl;

    @Override
    public boolean login(String username, String password) {
        Optional<Authentication> authentication = authenticationRepository.findByUsername(username);
        return authentication.isPresent() && bCryptPasswordEncoder.matches(password, authentication.get().getPasswordHash());
    }

    @Override
    public boolean signup(String username, String password) {
        Optional<Authentication> existingUser = authenticationRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            return false;
        }

        String userID = counterServiceImpl.getNextUserID();

        User user = new User();
        user.setUserID(userID);
        user.setRole(Role.CUSTOMER); // Mặc định role là USER

        Authentication authentication = new Authentication();
        authentication.setUserID(user);
        authentication.setUsername(username);
        authentication.setPasswordHash(bCryptPasswordEncoder.encode(password));
        authenticationRepository.save(authentication);
        userRepository.save(user);
        return true;
    }

//    @Override
//    public String loginWithGoogle(GoogleLoginDTO googleLoginDTO) {
//        Optional<Authentication> existingUser = authenticationRepository.findByUsername(googleLoginDTO.getEmail());
//
//        User user;
//        if (existingUser.isPresent()) {
//            user = existingUser.get().getUserID();
//        } else {
//            // Nếu user chưa tồn tại, tạo mới user mà không cần Authentication
//            user = new User();
//            user.setEmail(googleLoginDTO.getEmail());
//            user.setRole(Role.CUSTOMER); // Mặc định role là USER
//            userRepository.save(user);
//        }
//
//        // Lấy role từ user
//        Role role = user.getRole();
//
//        // Tạo JWT token
//        return jwtUtilHelper.generateToken(user.getEmail(), role.name());
//    }
//
//    @Override
//    public LoginDTO createAuthentication(String username, String password) {
//        Authentication authentication = new Authentication();
//        authentication.setUsername(username);
//        authentication.setPasswordHash(bCryptPasswordEncoder.encode(password));
//        authenticationRepository.save(authentication);
//
//        LoginDTO loginDTO = new LoginDTO();
//        loginDTO.setUsername(username);
//        loginDTO.setPassword(password);
//
//        return loginDTO;
//    }

}
