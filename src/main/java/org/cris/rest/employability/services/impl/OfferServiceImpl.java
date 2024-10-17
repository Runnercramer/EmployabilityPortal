package org.cris.rest.employability.services.impl;

import org.cris.rest.employability.models.dtos.OfferDTO;
import org.cris.rest.employability.models.dtos.UserDTO;
import org.cris.rest.employability.models.entities.Offer;
import org.cris.rest.employability.repositories.OfferRepository;
import org.cris.rest.employability.services.AbilitiesService;
import org.cris.rest.employability.services.EnterpriseService;
import org.cris.rest.employability.services.OfferService;
import org.cris.rest.employability.services.UserDataService;
import org.cris.rest.employability.services.mappers.OfferMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

    private OfferRepository offerRepository;
    private OfferMapper offerMapper;
    private AbilitiesService abilitiesService;
    private UserDataService userDataService;
    private EnterpriseService enterpriseService;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, OfferMapper offerMapper, AbilitiesService abilitiesService, UserDataService userDataService, EnterpriseService enterpriseService) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.abilitiesService = abilitiesService;
        this.userDataService = userDataService;
        this.enterpriseService = enterpriseService;
    }

    @Override
    public String createOffer(OfferDTO offerDTO) {
        UserDTO userDTO = this.userDataService.getUserByPersonalId(offerDTO.getCreatedBy());
        if (userDTO == null) return null;

        List<String> abilities = this.abilitiesService.saveAbilities(offerDTO.getAbilities());
        Offer offer = this.offerMapper.mapToOffer(offerDTO);
        offer.setEnterprise(this.enterpriseService.saveEnterprise(offerDTO.getEnterprise()));
        offer.setId(UUID.randomUUID().toString());
        offer.setAbilities(abilities);

        offerRepository.save(offer);
        return offer.getId();
    }

    @Override
    public OfferDTO getOfferById(String id) {
        Optional<Offer> offer = this.offerRepository.findById(id);
        if (offer.isPresent()) {
            OfferDTO offerDTO = this.offerMapper.mapToOfferDTO(offer.get());
            offerDTO.setEnterprise(this.enterpriseService.getEnterpriseById(offer.get().getEnterprise()));
            offerDTO.setAbilities(this.abilitiesService.getAbilitiesByIds(offer.get().getAbilities()));
            return offerDTO;
        }
        return null;
    }

    @Override
    public List<OfferDTO> getAllOffers() {
        List<Offer> offers = this.offerRepository.findAll();
        return this.mapOffers(offers);
    }

    @Override
    public String updateOffer(String id, OfferDTO offerDTO) {
        UserDTO userDTO = this.userDataService.getUserByPersonalId(offerDTO.getCreatedBy());
        if (userDTO == null) return null;
        Optional<Offer> offerPersisted = this.offerRepository.findById(id);
        if (offerPersisted.isPresent()) {

            List<String> abilities = this.abilitiesService.saveAbilities(offerDTO.getAbilities());
            Offer offerToPersist = this.offerMapper.mapToOffer(offerDTO);
            offerToPersist.setEnterprise(this.enterpriseService.saveEnterprise(offerDTO.getEnterprise()));
            offerToPersist.setId(id);
            offerToPersist.setAbilities(abilities);

            this.offerRepository.save(offerToPersist);
            return offerToPersist.getId();
        }
        return null;
    }

    @Override
    public List<OfferDTO> getOffersCreatedBy(String id) {
        List<Offer> offers = this.offerRepository.findOffersCreatedBy(id);
        return this.mapOffers(offers);
    }

    @Override
    public void deleteOffer(String id) {
        this.offerRepository.deleteById(id);
    }

    private List<OfferDTO> mapOffers(List<Offer> offers) {
        List<OfferDTO> response = new ArrayList<>();
        offers.forEach(offer -> {
            OfferDTO offerDTO = this.offerMapper.mapToOfferDTO(offer);
            offerDTO.setEnterprise(this.enterpriseService.getEnterpriseById(offer.getEnterprise()));
            offerDTO.setAbilities(this.abilitiesService.getAbilitiesByIds(offer.getAbilities()));
            response.add(offerDTO);
        });
        return response;
    }
}
