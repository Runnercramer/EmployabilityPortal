package org.cris.rest.employability.repositories;

import org.cris.rest.employability.models.entities.Postulation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostulationRepository extends MongoRepository<Postulation, String> {
    List<Postulation> findByApplicantId(String id);
}
