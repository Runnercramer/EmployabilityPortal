package org.cris.rest.employability.services.mappers;

import org.cris.rest.employability.models.dtos.OfferDTO;
import org.cris.rest.employability.models.entities.Offer;
import org.springframework.stereotype.Service;

@Service
public class OfferMapper {
    public Offer mapToOffer(OfferDTO offerDTO){
        Offer offer = new Offer();
        offer.setId(offerDTO.getId());
        offer.setCreationDate(offerDTO.getCreationDate());
        offer.setCreatedBy(offerDTO.getCreatedBy());
        offer.setTitle(offerDTO.getTitle());
        offer.setJobPosition(offerDTO.getJobPosition());
        offer.setActive(offerDTO.isActive());
        return offer;
    }

    public OfferDTO mapToOfferDTO(Offer offer){
        OfferDTO offerDTO = new OfferDTO();
        offerDTO.setId(offer.getId());
        offerDTO.setCreationDate(offer.getCreationDate());
        offerDTO.setCreatedBy(offer.getCreatedBy());
        offerDTO.setTitle(offer.getTitle());
        offerDTO.setJobPosition(offer.getJobPosition());
        offerDTO.setActive(offer.isActive());
        return offerDTO;
    }
}
