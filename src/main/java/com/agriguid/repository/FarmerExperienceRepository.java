package com.agriguid.repository;

import com.agriguid.model.FarmerExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FarmerExperienceRepository extends JpaRepository<FarmerExperience, Long> {
    List<FarmerExperience> findByCropId(Long cropId);
    List<FarmerExperience> findByUserId(Long userId);
    List<FarmerExperience> findByIsVerifiedTrueOrderByHelpfulCountDesc();
    List<FarmerExperience> findByCropIdAndIsVerifiedTrueOrderByHelpfulCountDesc(Long cropId);
    List<FarmerExperience> findByRegion(String region);
}