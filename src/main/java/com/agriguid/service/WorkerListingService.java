package com.agriguid.service;

import com.agriguid.model.WorkerListing;
import com.agriguid.model.AvailabilityStatus;
import com.agriguid.repository.WorkerListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerListingService {
    
    @Autowired
    private WorkerListingRepository workerListingRepository;
    
    public List<WorkerListing> getAllWorkerListings() {
        return workerListingRepository.findByIsActiveTrueOrderByListedDateDesc();
    }
    
    public Optional<WorkerListing> getWorkerListingById(Long id) {
        return workerListingRepository.findById(id);
    }
    
    public List<WorkerListing> getWorkersByRegion(String region) {
        return workerListingRepository.findByRegionAndIsActiveTrueOrderByListedDateDesc(region);
    }
    
    public List<WorkerListing> getAvailableWorkers(String region) {
        return workerListingRepository.findByRegionAndAvailability(region, AvailabilityStatus.AVAILABLE);
    }
    
    public WorkerListing saveWorkerListing(WorkerListing listing) {
        listing.setListedDate(LocalDateTime.now());
        listing.setIsActive(true);
        return workerListingRepository.save(listing);
    }
    
    public WorkerListing updateWorkerListing(Long id, WorkerListing listingDetails) {
        WorkerListing listing = workerListingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Worker listing not found with id: " + id));
        
        listing.setWorkerName(listingDetails.getWorkerName());
        listing.setPhoneNumber(listingDetails.getPhoneNumber());
        listing.setSkillSet(listingDetails.getSkillSet());
        listing.setExperienceYears(listingDetails.getExperienceYears());
        listing.setRegion(listingDetails.getRegion());
        listing.setSpecificLocation(listingDetails.getSpecificLocation());
        listing.setAvailability(listingDetails.getAvailability());
        listing.setDailyRate(listingDetails.getDailyRate());
        listing.setHourlyRate(listingDetails.getHourlyRate());
        listing.setDescription(listingDetails.getDescription());
        
        return workerListingRepository.save(listing);
    }
    
    public void deleteWorkerListing(Long id) {
        workerListingRepository.deleteById(id);
    }
    
    public List<WorkerListing> searchWorkersBySkill(String skillSet) {
        return workerListingRepository.findBySkillSetContainingIgnoreCase(skillSet);
    }
}