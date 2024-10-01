package org.cris.rest.employability.repositories;

import org.cris.rest.employability.models.entities.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<Job, String> {
}
