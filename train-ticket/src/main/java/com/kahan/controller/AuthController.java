package com.kahan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.kahan.entity.User;
import com.kahan.repository.UserRepository;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login-page")
    public String showLoginPage() {
        return "login-page";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String fullName,
                               @RequestParam String password) {

        // Check for existing username or email
        boolean userExists = userRepository.findByUsername(username).isPresent()
                || userRepository.findByEmail(email).isPresent();

        if (userExists) {
            return "redirect:/register?error=exists";
        }

        User user = User.builder()
                .username(username)
                .email(email)
                .fullName(fullName)
                .password(passwordEncoder.encode(password))
                .role("ROLE_USER")
                .build();

        userRepository.save(user);
        return "redirect:/login-page?registered=true";
    }

    @GetMapping("/redirect")
    public String redirectBasedOnRole(Authentication auth) {
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
