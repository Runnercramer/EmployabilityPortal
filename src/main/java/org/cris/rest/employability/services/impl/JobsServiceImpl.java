package org.cris.rest.employability.services.impl;

import org.cris.rest.employability.models.dtos.JobDTO;
import org.cris.rest.employability.models.entities.Job;
import org.cris.rest.employability.repositories.JobRepository;
import org.cris.rest.employability.services.AbilitiesService;
import org.cris.rest.employability.services.JobsService;
import org.cris.rest.employability.services.mappers.ProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobsServiceImpl implements JobsService {

    private JobRepository jobRepository;
    private ProfileMapper profileMapper;
    private AbilitiesService abilitiesService;

    @Autowired
    public JobsServiceImpl(JobRepository jobRepository, ProfileMapper profileMapper, AbilitiesService abilitiesService){
        this.jobRepository = jobRepository;
        this.profileMapper = profileMapper;
        this.abilitiesService = abilitiesService;
    }
    @Override
    public List<String> saveJobsInformation(List<JobDTO> jobDTOS) {
        List<String> response = new ArrayList<>();
        for(JobDTO job : jobDTOS){
            Job jobToPersist = this.profileMapper.mapToJob(job);
            jobToPersist.setAbilities(this.abilitiesService.saveAbilities(job.getAbilities()));
            jobRepository.save(jobToPersist);

            response.add(jobToPersist.getId());
        }
        return response;
    }

    @Override
    public List<JobDTO> findJobsById(List<String> ids) {
        List<Job> jobs = this.jobRepository.findAllById(ids);
        List<JobDTO> response = new ArrayList<>();
        for(Job job : jobs){
            JobDTO jobDTO = this.profileMapper.mapToJobDTO(job);
            jobDTO.setAbilities(this.abilitiesService.getAbilitiesByIds(job.getAbilities()));
            response.add(jobDTO);
        }
        return response;
    }
}
