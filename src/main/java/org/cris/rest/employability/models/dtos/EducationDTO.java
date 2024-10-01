package org.cris.rest.employability.models.dtos;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class EducationDTO {

    private String id;
    private String degree;
    private String institutionName;
    private Date startDate;
    private Date finalDate;
    private List<Map<String, String>> abilities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
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

    public List<Map<String, String>> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Map<String, String>> abilities) {
        this.abilities = abilities;
    }
}
