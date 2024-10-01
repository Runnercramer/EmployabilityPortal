package org.cris.rest.employability.repositories;

import org.cris.rest.employability.models.entities.Ability;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AbilityRepository extends MongoRepository<Ability, String> {
}
