package com.agriguid.model;

import jakarta.persistence.*;


@Entity
@Table(name = "crop_stages")
public class CropStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;
    
    private Integer stageNumber;
    private String stageName;
    private Integer durationDays;
    
    @Column(length = 2000)
    private String activities;
    
    @Column(length = 1000)
    private String pesticidesUsed;
    
    @Column(length = 1000)
    private String fertilizersUsed;
    
    @Column(length = 1000)
    private String wateringSchedule;

    public CropStage() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public Integer getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(Integer stageNumber) {
        this.stageNumber = stageNumber;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Integer getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(Integer durationDays) {
        this.durationDays = durationDays;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getPesticidesUsed() {
        return pesticidesUsed;
    }

    public void setPesticidesUsed(String pesticidesUsed) {
        this.pesticidesUsed = pesticidesUsed;
    }

    public String getFertilizersUsed() {
        return fertilizersUsed;
    }

    public void setFertilizersUsed(String fertilizersUsed) {
        this.fertilizersUsed = fertilizersUsed;
    }

    public String getWateringSchedule() {
        return wateringSchedule;
    }

    public void setWateringSchedule(String wateringSchedule) {
        this.wateringSchedule = wateringSchedule;
    }
}
