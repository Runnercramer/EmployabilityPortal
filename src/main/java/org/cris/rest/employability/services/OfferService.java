package org.cris.rest.employability.services;

import org.cris.rest.employability.models.dtos.OfferDTO;

import java.util.List;

public interface OfferService {
    String createOffer(OfferDTO offerDTO);
    OfferDTO getOfferById(String id);
    List<OfferDTO> getAllOffers();
    String updateOffer(String id, OfferDTO offerDTO);
    List<OfferDTO> getOffersCreatedBy(String id);
}
