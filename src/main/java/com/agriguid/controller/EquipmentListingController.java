package com.agriguid.controller;

import com.agriguid.model.EquipmentListing;
import com.agriguid.service.EquipmentListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@CrossOrigin(origins = "*")
public class EquipmentListingController {
    
    @Autowired
    private EquipmentListingService equipmentListingService;
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)  // Force JSON
    public ResponseEntity<List<EquipmentListing>> getAllEquipment() {
        return ResponseEntity.ok(equipmentListingService.getAllEquipmentListings());
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EquipmentListing> getEquipmentById(@PathVariable Long id) {
        return equipmentListingService.getEquipmentListingById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping(value = "/region/{region}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EquipmentListing>> getEquipmentByRegion(@PathVariable String region) {
        return ResponseEntity.ok(equipmentListingService.getEquipmentByRegion(region));
    }
    
    @GetMapping(value = "/available/{region}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EquipmentListing>> getAvailableEquipment(@PathVariable String region) {
        return ResponseEntity.ok(equipmentListingService.getAvailableEquipment(region));
    }
    
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EquipmentListing>> searchEquipment(@RequestParam String type) {
        return ResponseEntity.ok(equipmentListingService.searchEquipmentByType(type));
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EquipmentListing> createEquipmentListing(@RequestBody EquipmentListing listing) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(equipmentListingService.saveEquipmentListing(listing));
    }
    
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EquipmentListing> updateEquipmentListing(@PathVariable Long id, @RequestBody EquipmentListing listing) {
        return ResponseEntity.ok(equipmentListingService.updateEquipmentListing(id, listing));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipmentListing(@PathVariable Long id) {
        equipmentListingService.deleteEquipmentListing(id);
        return ResponseEntity.noContent().build();
    }
}