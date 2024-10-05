package org.cris.rest.employability.repositories;

import org.cris.rest.employability.models.entities.Enterprise;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnterpriseRepository extends MongoRepository<Enterprise, String> {
}
