package com.agriguid.service;

import com.agriguid.model.User;
import com.agriguid.model.UserRole;
import com.agriguid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public User saveUser(User user) {
        user.setRegistrationDate(LocalDateTime.now());
        user.setIsActive(true);
        return userRepository.save(user);
    }
    
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setFullName(userDetails.getFullName());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setLocation(userDetails.getLocation());
        user.setRegion(userDetails.getRegion());
        user.setRole(userDetails.getRole());
        user.setIsVerifiedFarmer(userDetails.getIsVerifiedFarmer());
        user.setYearsOfExperience(userDetails.getYearsOfExperience());
        user.setSpecialization(userDetails.getSpecialization());
        
        return userRepository.save(user);
    }
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    public List<User> getVerifiedFarmers() {
        return userRepository.findByIsVerifiedFarmerTrue();
    }
    
    public List<User> getUsersByRegion(String region) {
        return userRepository.findByRegion(region);
    }
}
