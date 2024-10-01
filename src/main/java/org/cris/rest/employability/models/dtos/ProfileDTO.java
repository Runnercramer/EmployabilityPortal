package org.cris.rest.employability.models.dtos;

import java.util.List;

public class ProfileDTO {
    private String id;
    private String personalId;
    private String title;
    private String subtitle;
    private String location;
    private Integer contacts;
    private List<JobDTO> workExperience;
    private String description;
    private List<EducationDTO> education;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getContacts() {
        return contacts;
    }

    public void setContacts(Integer contacts) {
        this.contacts = contacts;
    }

    public List<JobDTO> getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(List<JobDTO> workExperience) {
        this.workExperience = workExperience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EducationDTO> getEducation() {
        return education;
    }

    public void setEducation(List<EducationDTO> education) {
        this.education = education;
    }
}
