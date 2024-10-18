package org.cris.rest.employability.models.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class OfferDTO {
    private String id;
    private LocalDateTime creationDate;
    private String createdBy;
    private String jobPosition;
    private EnterpriseDTO enterprise;
    private String title;
    private List<Map<String, String>> abilities;
    private boolean isActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public EnterpriseDTO getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(EnterpriseDTO enterprise) {
        this.enterprise = enterprise;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Map<String, String>> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Map<String, String>> abilities) {
        this.abilities = abilities;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
