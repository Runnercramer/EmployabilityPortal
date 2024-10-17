package org.cris.rest.employability.services.impl;

import org.cris.rest.employability.models.dtos.OfferDTO;
import org.cris.rest.employability.models.dtos.PostulationDTO;
import org.cris.rest.employability.models.dtos.ProfileDTO;
import org.cris.rest.employability.models.entities.Postulation;
import org.cris.rest.employability.repositories.PostulationRepository;
import org.cris.rest.employability.services.OfferService;
import org.cris.rest.employability.services.PostulationService;
import org.cris.rest.employability.services.ProfilesService;
import org.cris.rest.employability.services.mappers.PostulationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostulationServiceImpl implements PostulationService {

    private PostulationRepository postulationRepository;
    private OfferService offerService;
    private ProfilesService profilesService;
    private PostulationMapper postulationMapper;

    @Autowired
    public PostulationServiceImpl(PostulationRepository postulationRepository, OfferService offerService, ProfilesService profilesService, PostulationMapper postulationMapper) {
        this.postulationRepository = postulationRepository;
        this.offerService = offerService;
        this.profilesService = profilesService;
        this.postulationMapper = postulationMapper;
    }

    @Override
    public String createPostulation(PostulationDTO postulationDTO) {
        ProfileDTO profileDTO = this.profilesService.getProfileById(postulationDTO.getApplicantId());
        OfferDTO offerDTO = this.offerService.getOfferById(postulationDTO.getOfferId());
        if (this.validatePostulationCreation(profileDTO, offerDTO)) {
            Postulation postulation = this.postulationMapper.mapToPostulation(postulationDTO);
            postulation.setId(UUID.randomUUID().toString());
            postulationRepository.save(postulation);
            return postulation.getId();
        }
        return null;
    }

    @Override
    public List<PostulationDTO> getPostulations() {
        List<Postulation> postulations = this.postulationRepository.findAll();
        return postulations.stream().map(postulation -> this.postulationMapper.mapToPostulationDTO(postulation)).toList();
    }

    @Override
    public PostulationDTO findPostulationById(String id) {
        Optional<Postulation> postulation = this.postulationRepository.findById(id);
        return postulation.map(postulationMapper::mapToPostulationDTO).orElse(null);
    }

    @Override
    public String updatePostulation(PostulationDTO postulationDTO, String id) {
        Optional<Postulation> currentPostulation = this.postulationRepository.findById(id);
        if (currentPostulation.isPresent()) {
            ProfileDTO profileDTO = this.profilesService.getProfileById(postulationDTO.getApplicantId());
            OfferDTO offerDTO = this.offerService.getOfferById(postulationDTO.getOfferId());
            if (this.validatePostulationCreation(profileDTO, offerDTO)) {
                Postulation postulation = this.postulationMapper.mapToPostulation(postulationDTO);
                postulation.setId(id);
                this.postulationRepository.save(postulation);
                return postulation.getId();
            }
        }
        return null;
    }

    @Override
    public List<PostulationDTO> findByApplicantId(String id) {
        List<Postulation> postulations = this.postulationRepository.findByApplicantId(id);
        if (postulations != null && !postulations.isEmpty()){
            return postulations.stream().map(postulationMapper::mapToPostulationDTO).toList();
        }
        return null;
    }

    private boolean validatePostulationCreation(ProfileDTO profileDTO, OfferDTO offerDTO) {
        boolean response = profileDTO != null;
        if (response) response = offerDTO != null;
        return response;
    }
}
