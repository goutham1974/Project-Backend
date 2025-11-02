package com.agriguid.repository;

import com.agriguid.model.CropStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CropStageRepository extends JpaRepository<CropStage, Long> {
    List<CropStage> findByCropIdOrderByStageNumber(Long cropId);
}