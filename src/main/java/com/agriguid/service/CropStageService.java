package com.agriguid.service;

import com.agriguid.model.CropStage;
import com.agriguid.repository.CropStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CropStageService {
    
    @Autowired
    private CropStageRepository cropStageRepository;
    
    public List<CropStage> getAllStages() {
        return cropStageRepository.findAll();
    }
    
    public Optional<CropStage> getStageById(Long id) {
        return cropStageRepository.findById(id);
    }
    
    public List<CropStage> getStagesByCropId(Long cropId) {
        return cropStageRepository.findByCropIdOrderByStageNumber(cropId);
    }
    
    public CropStage saveStage(CropStage stage) {
        return cropStageRepository.save(stage);
    }
    
    public CropStage updateStage(Long id, CropStage stageDetails) {
        CropStage stage = cropStageRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Stage not found with id: " + id));
        
        stage.setStageNumber(stageDetails.getStageNumber());
        stage.setStageName(stageDetails.getStageName());
        stage.setDurationDays(stageDetails.getDurationDays());
        stage.setActivities(stageDetails.getActivities());
        stage.setPesticidesUsed(stageDetails.getPesticidesUsed());
        stage.setFertilizersUsed(stageDetails.getFertilizersUsed());
        stage.setWateringSchedule(stageDetails.getWateringSchedule());
        
        return cropStageRepository.save(stage);
    }
    
    public void deleteStage(Long id) {
        cropStageRepository.deleteById(id);
    }
}
