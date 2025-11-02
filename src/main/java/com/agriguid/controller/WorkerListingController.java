package com.agriguid.controller;

import com.agriguid.model.WorkerListing;
import com.agriguid.service.WorkerListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/workers")
@CrossOrigin(origins = "*")
public class WorkerListingController {
    
    @Autowired
    private WorkerListingService workerListingService;
    
    @GetMapping
    public ResponseEntity<List<WorkerListing>> getAllWorkers() {
        return ResponseEntity.ok(workerListingService.getAllWorkerListings());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<WorkerListing> getWorkerById(@PathVariable Long id) {
        return workerListingService.getWorkerListingById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/region/{region}")
    public ResponseEntity<List<WorkerListing>> getWorkersByRegion(@PathVariable String region) {
        return ResponseEntity.ok(workerListingService.getWorkersByRegion(region));
    }
    
    @GetMapping("/available/{region}")
    public ResponseEntity<List<WorkerListing>> getAvailableWorkers(@PathVariable String region) {
        return ResponseEntity.ok(workerListingService.getAvailableWorkers(region));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<WorkerListing>> searchWorkers(@RequestParam String skill) {
        return ResponseEntity.ok(workerListingService.searchWorkersBySkill(skill));
    }
    
    @PostMapping
    public ResponseEntity<WorkerListing> createWorkerListing(@RequestBody WorkerListing listing) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(workerListingService.saveWorkerListing(listing));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<WorkerListing> updateWorkerListing(@PathVariable Long id, @RequestBody WorkerListing listing) {
        return ResponseEntity.ok(workerListingService.updateWorkerListing(id, listing));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkerListing(@PathVariable Long id) {
        workerListingService.deleteWorkerListing(id);
        return ResponseEntity.noContent().build();
    }
}
