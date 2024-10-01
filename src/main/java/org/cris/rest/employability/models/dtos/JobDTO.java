package org.cris.rest.employability.models.dtos;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class JobDTO {
    private String id;
    private String title;
    private String jobPosition;
    private Date startDate;
    private Date finalDate;
    private String description;
    private List<Map<String, String>> abilities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Map<String, String>> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Map<String, String>> abilities) {
        this.abilities = abilities;
    }
}
