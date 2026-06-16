package com.EMPMANAGE.Service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.EMPMANAGE.DTO.AuthResponseDTO;
import com.EMPMANAGE.DTO.LoginRquestDTO;
import com.EMPMANAGE.DTO.RegisterRequestDTO;
import com.EMPMANAGE.Entity.UserAuthentication;
import com.EMPMANAGE.Repository.UserAuthRepository;
import com.EMPMANAGE.Security.JWTUtil;

@Service
public class UserAuthService {

    private final UserAuthRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public UserAuthService(UserAuthRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponseDTO register(RegisterRequestDTO request) {
        if (userRepository.findByUserOfficialEmail(request.getUserOfficialEmail()).isPresent()) {
            throw new RuntimeException("User already exists with email: " + request.getUserOfficialEmail());
        }

        UserAuthentication user = new UserAuthentication();
        user.setUsername(request.getUsername());
        user.setUserOfficialEmail(request.getUserOfficialEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);

        String token = jwtUtil.generateToken(user);
        return new AuthResponseDTO();
    }

    public AuthResponseDTO authenticate(LoginRquestDTO request) {
        UserAuthentication user = userRepository
                .findByUserOfficialEmail(request.getUserOfficialEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user);
        return new AuthResponseDTO();
    }
}