package com.agriguid.service;

import com.agriguid.model.EquipmentListing;
import com.agriguid.model.AvailabilityStatus;
import com.agriguid.repository.EquipmentListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentListingService {
    
    @Autowired
    private EquipmentListingRepository equipmentListingRepository;
    
    public List<EquipmentListing> getAllEquipmentListings() {
        return equipmentListingRepository.findByIsActiveTrueOrderByListedDateDesc();
    }
    
    public Optional<EquipmentListing> getEquipmentListingById(Long id) {
        return equipmentListingRepository.findById(id);
    }
    
    public List<EquipmentListing> getEquipmentByRegion(String region) {
        return equipmentListingRepository.findByRegionAndIsActiveTrueOrderByListedDateDesc(region);
    }
    
    public List<EquipmentListing> getAvailableEquipment(String region) {
        return equipmentListingRepository.findByRegionAndAvailability(region, AvailabilityStatus.AVAILABLE);
    }
    
    public EquipmentListing saveEquipmentListing(EquipmentListing listing) {
        listing.setListedDate(LocalDateTime.now());
        listing.setIsActive(true);
        return equipmentListingRepository.save(listing);
    }
    
    public EquipmentListing updateEquipmentListing(Long id, EquipmentListing listingDetails) {
        EquipmentListing listing = equipmentListingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Equipment listing not found with id: " + id));
        
        listing.setEquipmentName(listingDetails.getEquipmentName());
        listing.setEquipmentType(listingDetails.getEquipmentType());
        listing.setBrand(listingDetails.getBrand());
        listing.setModel(listingDetails.getModel());
        listing.setDescription(listingDetails.getDescription());
        listing.setRegion(listingDetails.getRegion());
        listing.setSpecificLocation(listingDetails.getSpecificLocation());
        listing.setPhoneNumber(listingDetails.getPhoneNumber());
        listing.setAvailability(listingDetails.getAvailability());
        listing.setDailyRentalCost(listingDetails.getDailyRentalCost());
        listing.setHourlyRentalCost(listingDetails.getHourlyRentalCost());
        listing.setCondition(listingDetails.getCondition());
        
        return equipmentListingRepository.save(listing);
    }
    
    public void deleteEquipmentListing(Long id) {
        equipmentListingRepository.deleteById(id);
    }
    
    public List<EquipmentListing> searchEquipmentByType(String equipmentType) {
        return equipmentListingRepository.findByEquipmentTypeContainingIgnoreCase(equipmentType);
    }
}