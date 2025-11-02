package com.agriguid.repository;

import com.agriguid.model.WorkerListing;
import com.agriguid.model.AvailabilityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WorkerListingRepository extends JpaRepository<WorkerListing, Long> {
    List<WorkerListing> findByRegion(String region);
    List<WorkerListing> findByAvailability(AvailabilityStatus availability);
    List<WorkerListing> findByRegionAndAvailability(String region, AvailabilityStatus availability);
    List<WorkerListing> findByIsActiveTrueOrderByListedDateDesc();
    List<WorkerListing> findByRegionAndIsActiveTrueOrderByListedDateDesc(String region);
    List<WorkerListing> findBySkillSetContainingIgnoreCase(String skillSet);
}