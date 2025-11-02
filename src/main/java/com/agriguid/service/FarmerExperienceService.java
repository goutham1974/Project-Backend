package com.agriguid.service;

import com.agriguid.model.FarmerExperience;
import com.agriguid.repository.FarmerExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FarmerExperienceService {
    
    @Autowired
    private FarmerExperienceRepository experienceRepository;
    
    public List<FarmerExperience> getAllExperiences() {
        return experienceRepository.findAll();
    }
    
    public Optional<FarmerExperience> getExperienceById(Long id) {
        return experienceRepository.findById(id);
    }
    
    public List<FarmerExperience> getExperiencesByCropId(Long cropId) {
        return experienceRepository.findByCropIdAndIsVerifiedTrueOrderByHelpfulCountDesc(cropId);
    }
    
    public List<FarmerExperience> getExperiencesByUserId(Long userId) {
        return experienceRepository.findByUserId(userId);
    }
    
    public FarmerExperience saveExperience(FarmerExperience experience) {
        experience.setPostedDate(LocalDateTime.now());
        experience.setHelpfulCount(0);
        experience.setIsVerified(false);
        return experienceRepository.save(experience);
    }
    
    public FarmerExperience updateExperience(Long id, FarmerExperience expDetails) {
        FarmerExperience experience = experienceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Experience not found with id: " + id));
        
        experience.setTitle(expDetails.getTitle());
        experience.setExperienceText(expDetails.getExperienceText());
        experience.setYearsGrowing(expDetails.getYearsGrowing());
        experience.setRegion(expDetails.getRegion());
        experience.setAverageYield(expDetails.getAverageYield());
        experience.setTipsAndTricks(expDetails.getTipsAndTricks());
        experience.setChallengesFaced(expDetails.getChallengesFaced());
        
        return experienceRepository.save(experience);
    }
    
    public FarmerExperience incrementHelpfulCount(Long id) {
        FarmerExperience experience = experienceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Experience not found with id: " + id));
        
        experience.setHelpfulCount(experience.getHelpfulCount() + 1);
        return experienceRepository.save(experience);
    }
    
    public void deleteExperience(Long id) {
        experienceRepository.deleteById(id);
    }
    
    public List<FarmerExperience> getTopExperiences() {
        return experienceRepository.findByIsVerifiedTrueOrderByHelpfulCountDesc();
    }
}
