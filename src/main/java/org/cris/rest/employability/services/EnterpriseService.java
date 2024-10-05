package org.cris.rest.employability.services;

import org.cris.rest.employability.models.dtos.EnterpriseDTO;

public interface EnterpriseService {
    String saveEnterprise(EnterpriseDTO enterpriseDTO);
    EnterpriseDTO getEnterpriseById(String id);
}
