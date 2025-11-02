package com.agriguid.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "farmer_experiences")
public class FarmerExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;
    
    private String title;
    
    @Column(length = 3000)
    private String experienceText;
    
    private Integer yearsGrowing;
    private String region;
    private Double averageYield;
    
    @Column(length = 1000)
    private String tipsAndTricks;
    
    @Column(length = 1000)
    private String challengesFaced;
    
    private Integer helpfulCount;
    private LocalDateTime postedDate;
    private Boolean isVerified;

    public FarmerExperience() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExperienceText() {
        return experienceText;
    }

    public void setExperienceText(String experienceText) {
        this.experienceText = experienceText;
    }

    public Integer getYearsGrowing() {
        return yearsGrowing;
    }

    public void setYearsGrowing(Integer yearsGrowing) {
        this.yearsGrowing = yearsGrowing;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Double getAverageYield() {
        return averageYield;
    }

    public void setAverageYield(Double averageYield) {
        this.averageYield = averageYield;
    }

    public String getTipsAndTricks() {
        return tipsAndTricks;
    }

    public void setTipsAndTricks(String tipsAndTricks) {
        this.tipsAndTricks = tipsAndTricks;
    }

    public String getChallengesFaced() {
        return challengesFaced;
    }

    public void setChallengesFaced(String challengesFaced) {
        this.challengesFaced = challengesFaced;
    }

    public Integer getHelpfulCount() {
        return helpfulCount;
    }

    public void setHelpfulCount(Integer helpfulCount) {
        this.helpfulCount = helpfulCount;
    }

    public LocalDateTime getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDateTime postedDate) {
        this.postedDate = postedDate;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }
}
