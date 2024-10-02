package org.cris.rest.employability.services;

import org.cris.rest.employability.models.dtos.JobDTO;

import java.util.List;

public interface JobsService {
    List<String> saveJobsInformation(List<JobDTO> jobDTOS);
    List<JobDTO> findJobsById(List<String> ids);
}
