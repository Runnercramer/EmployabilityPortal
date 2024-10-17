package org.cris.rest.employability.services;

import org.cris.rest.employability.models.dtos.PostulationDTO;

import java.util.List;

public interface PostulationService {
    String createPostulation(PostulationDTO postulationDTO);
    List<PostulationDTO> getPostulations();
    PostulationDTO findPostulationById(String id);
    String updatePostulation(PostulationDTO postulationDTO, String id);
    List<PostulationDTO> findByApplicantId(String id);
}
