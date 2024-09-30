package org.cris.rest.employability.repositories;

import org.cris.rest.employability.models.entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByPersonalId(String personalId);
}
