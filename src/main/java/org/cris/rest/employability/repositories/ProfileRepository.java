package org.cris.rest.employability.repositories;

import org.cris.rest.employability.models.entities.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile, String> {
}
