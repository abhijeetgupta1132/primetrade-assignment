package com.abhijeet.primetrade.service;

import com.abhijeet.primetrade.dto.LoginRequest;
import com.abhijeet.primetrade.dto.LoginResponse;
import com.abhijeet.primetrade.dto.RegisterRequest;
import com.abhijeet.primetrade.entity.Role;
import com.abhijeet.primetrade.entity.User;
import com.abhijeet.primetrade.repository.UserRepository;
import com.abhijeet.primetrade.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // ================= REGISTER =================
    public String register(RegisterRequest request) {

        System.out.println("📝 REGISTER ATTEMPT: " + request.getEmail());

        if (userRepository.existsByEmail(request.getEmail())) {
            System.out.println("❌ EMAIL ALREADY EXISTS");
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        System.out.println("✅ USER REGISTERED SUCCESSFULLY");

        return "User register successfully";
    }

    // ================= LOGIN =================
    public LoginResponse login(LoginRequest request) {

        System.out.println("🔐 LOGIN ATTEMPT: " + request.getEmail());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    System.out.println("❌ USER NOT FOUND IN DB");
                    return new RuntimeException("Invalid email or password");
                });

        System.out.println("✅ USER FOUND IN DB");

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            System.out.println("❌ PASSWORD DOES NOT MATCH");
            throw new RuntimeException("Invalid email or password");
        }

        System.out.println("✅ PASSWORD MATCHED");

        // generate JWT token
        String token = jwtUtil.generateToken(user.getEmail());

        System.out.println("🎉 LOGIN SUCCESS");

        return new LoginResponse(token);
    }
}