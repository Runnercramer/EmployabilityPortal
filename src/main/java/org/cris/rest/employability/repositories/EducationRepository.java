package org.cris.rest.employability.repositories;

import org.cris.rest.employability.models.entities.Education;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EducationRepository extends MongoRepository<Education, String> {
}
