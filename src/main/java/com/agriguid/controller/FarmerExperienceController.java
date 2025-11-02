package com.agriguid.controller;

import com.agriguid.model.FarmerExperience;
import com.agriguid.service.FarmerExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/experiences")
@CrossOrigin(origins = "*")
public class FarmerExperienceController {
    
    @Autowired
    private FarmerExperienceService experienceService;
    
    @GetMapping
    public ResponseEntity<List<FarmerExperience>> getAllExperiences() {
        return ResponseEntity.ok(experienceService.getAllExperiences());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FarmerExperience> getExperienceById(@PathVariable Long id) {
        return experienceService.getExperienceById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/crop/{cropId}")
    public ResponseEntity<List<FarmerExperience>> getExperiencesByCrop(@PathVariable Long cropId) {
        return ResponseEntity.ok(experienceService.getExperiencesByCropId(cropId));
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FarmerExperience>> getExperiencesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(experienceService.getExperiencesByUserId(userId));
    }
    
    @GetMapping("/top")
    public ResponseEntity<List<FarmerExperience>> getTopExperiences() {
        return ResponseEntity.ok(experienceService.getTopExperiences());
    }
    
    @PostMapping
    public ResponseEntity<FarmerExperience> createExperience(@RequestBody FarmerExperience experience) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(experienceService.saveExperience(experience));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<FarmerExperience> updateExperience(@PathVariable Long id, @RequestBody FarmerExperience experience) {
        return ResponseEntity.ok(experienceService.updateExperience(id, experience));
    }
    
    @PostMapping("/{id}/helpful")
    public ResponseEntity<FarmerExperience> markAsHelpful(@PathVariable Long id) {
        return ResponseEntity.ok(experienceService.incrementHelpfulCount(id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.noContent().build();
    }
}
