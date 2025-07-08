package com.kahan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kahan.entity.User;
import com.kahan.repository.UserRepository;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication; // ✅ ADD THIS!

@SpringBootApplication
public class TrainTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainTicketApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
if (repo.findByUsername("admin") == null) {
                User admin = User.builder()
                        .username("admin")
                        .password(encoder.encode("admin123"))
                        .role("ROLE_ADMIN")
                        .build();
                repo.save(admin);
            }
        };
    }
}
