package org.cris.rest.employability.services.impl;

import org.cris.rest.employability.models.dtos.ProfileDTO;
import org.cris.rest.employability.models.entities.Profile;
import org.cris.rest.employability.repositories.ProfileRepository;
import org.cris.rest.employability.services.EducationService;
import org.cris.rest.employability.services.JobsService;
import org.cris.rest.employability.services.ProfilesService;
import org.cris.rest.employability.services.mappers.ProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfilesServiceImpl implements ProfilesService {

    private ProfileRepository profileRepository;
    private ProfileMapper profileMapper;
    private JobsService jobsService;
    private EducationService educationService;

    @Autowired
    public ProfilesServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper, JobsService jobsService, EducationService educationService) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
        this.jobsService = jobsService;
        this.educationService = educationService;
    }

    @Override
    public String createProfile(ProfileDTO profileDTO) {
        List<String> workExperience = this.jobsService.saveJobsInformation(profileDTO.getWorkExperience());
        List<String> educationIds = this.educationService.saveEducationInformation(profileDTO.getEducation());
        Profile profile = this.profileMapper.mapToProfile(profileDTO);
        profile.setWorkExperience(workExperience);
        profile.setEducation(educationIds);

        profileRepository.save(profile);
        return profile.getId();
    }

}
