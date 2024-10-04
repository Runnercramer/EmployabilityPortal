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
import java.util.Optional;

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
        Profile profile = this.persistProfile(profileDTO);
        return profile.getId();
    }

    @Override
    public ProfileDTO getProfileById(String id) {
        Optional<Profile> profile = this.profileRepository.findById(id);
        if (profile.isPresent()) {
            ProfileDTO profileDTO = this.profileMapper.mapToProfileDTO(profile.get());
            profileDTO.setWorkExperience(this.jobsService.findJobsById(profile.get().getWorkExperience()));
            profileDTO.setEducation(this.educationService.getEducationByIds(profile.get().getEducation()));
            return profileDTO;
        }
        return null;
    }

    @Override
    public String updateProfile(String id, ProfileDTO profileDTO) {
        Optional<Profile> profile = this.profileRepository.findById(id);
        if (profile.isPresent()){
            profileDTO.setId(id);
            Profile profilePersisted = this.persistProfile(profileDTO);
            return profilePersisted.getId();
        }
            return null;
    }

    private Profile persistProfile(ProfileDTO profileDTO){
        List<String> workExperience = this.jobsService.saveJobsInformation(profileDTO.getWorkExperience());
        List<String> educationIds = this.educationService.saveEducationInformation(profileDTO.getEducation());
        Profile profileToPersist = this.profileMapper.mapToProfile(profileDTO);
        profileToPersist.setWorkExperience(workExperience);
        profileToPersist.setEducation(educationIds);

        profileRepository.save(profileToPersist);
        return profileToPersist;
    }

}
