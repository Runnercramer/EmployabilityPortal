package org.cris.rest.employability.services;

import org.cris.rest.employability.models.dtos.EducationDTO;

import java.util.List;

public interface EducationService {
    List<String> saveEducationInformation(List<EducationDTO> educationDTOS);
}
