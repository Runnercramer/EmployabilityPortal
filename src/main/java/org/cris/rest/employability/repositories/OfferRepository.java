package org.cris.rest.employability.repositories;

import org.cris.rest.employability.models.entities.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OfferRepository extends MongoRepository<Offer, String> {
    @Query("{ 'createdBy': ?0 }")
    List<Offer> findOffersCreatedBy(String personalId);
}
