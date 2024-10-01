package org.cris.rest.employability.services.mappers;

import org.cris.rest.employability.models.dtos.EducationDTO;
import org.cris.rest.employability.models.dtos.JobDTO;
import org.cris.rest.employability.models.dtos.ProfileDTO;
import org.cris.rest.employability.models.entities.Education;
import org.cris.rest.employability.models.entities.Job;
import org.cris.rest.employability.models.entities.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileMapper {

    public Profile mapToProfile(ProfileDTO profileDTO){
        Profile profile = new Profile();
        profile.setId(UUID.randomUUID().toString());
        profile.setPersonalId(profileDTO.getPersonalId());
        profile.setTitle(profileDTO.getTitle());
        profile.setSubtitle(profileDTO.getSubtitle());
        profile.setLocation(profileDTO.getLocation());
        profile.setContacts(profileDTO.getContacts());
        profile.setDescription(profileDTO.getDescription());
        return profile;
    }

    public Job mapToJob(JobDTO jobDTO){
        Job job = new Job();
        job.setId(UUID.randomUUID().toString());
        job.setTitle(jobDTO.getTitle());
        job.setJobPosition(jobDTO.getJobPosition());
        job.setDescription(jobDTO.getDescription());
        job.setStartDate(jobDTO.getStartDate());
        job.setFinalDate(jobDTO.getFinalDate());
        return job;
    }

    public Education mapToEducation(EducationDTO educationDTO){
        Education education = new Education();
        education.setId(UUID.randomUUID().toString());
        education.setDegree(educationDTO.getDegree());
        education.setInstitutionName(educationDTO.getInstitutionName());
        education.setStartDate(educationDTO.getStartDate());
        education.setFinalDate(educationDTO.getFinalDate());
        return education;
    }
}
