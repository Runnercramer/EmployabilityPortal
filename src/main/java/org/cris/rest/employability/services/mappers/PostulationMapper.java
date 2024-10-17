package org.cris.rest.employability.services.mappers;

import org.cris.rest.employability.models.dtos.PostulationDTO;
import org.cris.rest.employability.models.entities.Postulation;
import org.springframework.stereotype.Service;

@Service
public class PostulationMapper {
    public Postulation mapToPostulation(PostulationDTO postulationDTO){
        Postulation postulation = new Postulation();
        postulation.setId(postulationDTO.getId());
        postulation.setApplicantId(postulationDTO.getApplicantId());
        postulation.setOfferId(postulationDTO.getOfferId());
        postulation.setCreationDate(postulationDTO.getCreationDate());
        postulation.setState(postulationDTO.getState());
        return postulation;
    }

    public PostulationDTO mapToPostulationDTO(Postulation postulation){
        PostulationDTO postulationDTO = new PostulationDTO();
        postulationDTO.setId(postulation.getId());
        postulationDTO.setApplicantId(postulation.getApplicantId());
        postulationDTO.setOfferId(postulation.getOfferId());
        postulationDTO.setCreationDate(postulation.getCreationDate());
        postulationDTO.setState(postulation.getState());
        return postulationDTO;
    }
}
