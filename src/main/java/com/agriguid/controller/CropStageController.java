package com.agriguid.controller;

import com.agriguid.model.CropStage;
import com.agriguid.service.CropStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/crop-stages")
@CrossOrigin(origins = "*")
public class CropStageController {
    
    @Autowired
    private CropStageService cropStageService;
    
    @GetMapping
    public ResponseEntity<List<CropStage>> getAllStages() {
        return ResponseEntity.ok(cropStageService.getAllStages());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CropStage> getStageById(@PathVariable Long id) {
        return cropStageService.getStageById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/crop/{cropId}")
    public ResponseEntity<List<CropStage>> getStagesByCrop(@PathVariable Long cropId) {
        return ResponseEntity.ok(cropStageService.getStagesByCropId(cropId));
    }
    
    @PostMapping
    public ResponseEntity<CropStage> createStage(@RequestBody CropStage stage) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(cropStageService.saveStage(stage));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CropStage> updateStage(@PathVariable Long id, @RequestBody CropStage stage) {
        return ResponseEntity.ok(cropStageService.updateStage(id, stage));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable Long id) {
        cropStageService.deleteStage(id);
        return ResponseEntity.noContent().build();
    }
}
