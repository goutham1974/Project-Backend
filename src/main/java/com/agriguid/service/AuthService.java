package com.agriguid.service;

import com.agriguid.dto.LoginRequest;
import com.agriguid.dto.SignupRequest;
import com.agriguid.dto.AuthResponse;
import com.agriguid.model.User;
import com.agriguid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    // Simple password encoding (In production, use BCryptPasswordEncoder)
    private String encodePassword(String password) {
        // For now, storing plain text. In production, use:
        // return new BCryptPasswordEncoder().encode(password);
        return password;
    }
    
    private boolean matchPassword(String rawPassword, String encodedPassword) {
        // For now, simple comparison. In production, use:
        // return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
        return rawPassword.equals(encodedPassword);
    }
    
    public AuthResponse signup(SignupRequest request) {
        // Validate username
        if (userRepository.existsByUsername(request.getUsername())) {
            return new AuthResponse("Username already exists", false);
        }
        
        // Validate email
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse("Email already exists", false);
        }
        
        // Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encodePassword(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setLocation(request.getLocation());
        user.setRegion(request.getRegion());
        user.setRole(request.getRole());
        user.setYearsOfExperience(request.getYearsOfExperience());
        user.setSpecialization(request.getSpecialization());
        user.setRegistrationDate(LocalDateTime.now());
        user.setIsActive(true);
        user.setIsVerifiedFarmer(false); // Admin will verify later
        
        User savedUser = userRepository.save(user);
        
        // Don't send password in response
        savedUser.setPassword(null);
        
        return new AuthResponse("User registered successfully", true, savedUser);
    }
    
    public AuthResponse login(LoginRequest request) {
        // Find user by username
        User user = userRepository.findByUsername(request.getUsername())
            .orElse(null);
        
        if (user == null) {
            return new AuthResponse("Invalid username or password", false);
        }
        
        // Check if user is active
        if (!user.getIsActive()) {
            return new AuthResponse("Account is deactivated. Contact admin.", false);
        }
        
        // Verify password
        if (!matchPassword(request.getPassword(), user.getPassword())) {
            return new AuthResponse("Invalid username or password", false);
        }
        
        // Don't send password in response
        user.setPassword(null);
        
        return new AuthResponse("Login successful", true, user);
    }
    
    public User getCurrentUser(Long userId) {
        return userRepository.findById(userId)
            .map(user -> {
                user.setPassword(null); // Don't send password
                return user;
            })
            .orElse(null);
    }
    
    public AuthResponse changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId).orElse(null);
        
        if (user == null) {
            return new AuthResponse("User not found", false);
        }
        
        if (!matchPassword(oldPassword, user.getPassword())) {
            return new AuthResponse("Current password is incorrect", false);
        }
        
        user.setPassword(encodePassword(newPassword));
        userRepository.save(user);
        
        return new AuthResponse("Password changed successfully", true);
    }
}
