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
            jobToPersist.setAbilities(abilitiesService.saveAbilities(job.getAbilities()));
            jobRepository.save(jobToPersist);

            response.add(jobToPersist.getId());
        }
        return response;
    }
}
