package com.grvDemo.crudDemo.controller;

import com.grvDemo.crudDemo.entity.Role;
import com.grvDemo.crudDemo.entity.User;
import com.grvDemo.crudDemo.repository.UserRepository;
import com.grvDemo.crudDemo.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository repo, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        repo.save(user);
        return "User Registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User existing = repo.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(user.getPassword(), existing.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String role = existing.getRole() != null
                ? existing.getRole().name()
                : Role.USER.name();

        return jwtUtil.generateToken(
                existing.getUsername(),
                role
        );
    }
}
