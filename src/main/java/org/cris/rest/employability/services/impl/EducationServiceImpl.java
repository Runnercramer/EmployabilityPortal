package org.cris.rest.employability.services.impl;

import org.cris.rest.employability.models.dtos.EducationDTO;
import org.cris.rest.employability.models.dtos.JobDTO;
import org.cris.rest.employability.models.entities.Education;
import org.cris.rest.employability.models.entities.Job;
import org.cris.rest.employability.repositories.EducationRepository;
import org.cris.rest.employability.services.AbilitiesService;
import org.cris.rest.employability.services.EducationService;
import org.cris.rest.employability.services.mappers.ProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    private EducationRepository educationRepository;
    private ProfileMapper profileMapper;
    private AbilitiesService abilitiesService;

    @Autowired
    public EducationServiceImpl (EducationRepository educationRepository, ProfileMapper profileMapper, AbilitiesService abilitiesService) {
        this.educationRepository = educationRepository;
        this.profileMapper = profileMapper;
        this.abilitiesService = abilitiesService;
    }
    @Override
    public List<String> saveEducationInformation(List<EducationDTO> educationDTOS) {
        List<String> response = new ArrayList<>();
        for (EducationDTO educationDTO: educationDTOS){
            Education education = this.profileMapper.mapToEducation(educationDTO);
            education.setAbilities(this.abilitiesService.saveAbilities(educationDTO.getAbilities()));
            this.educationRepository.save(education);

            response.add(education.getId());
        }
        return response;
    }

    @Override
    public List<EducationDTO> getEducationByIds(List<String> ids) {
        List<Education> educations = this.educationRepository.findAllById(ids);
        List<EducationDTO> response = new ArrayList<>();
        for(Education education : educations){
            EducationDTO educationDTO = this.profileMapper.mapToEducationDTO(education);
            educationDTO.setAbilities(this.abilitiesService.getAbilitiesByIds(education.getAbilities()));
            response.add(educationDTO);
        }
        return response;
    }
}
