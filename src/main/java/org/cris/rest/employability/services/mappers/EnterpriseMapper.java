package org.cris.rest.employability.services.mappers;

import org.cris.rest.employability.models.dtos.EnterpriseDTO;
import org.cris.rest.employability.models.entities.Enterprise;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseMapper {

    public Enterprise mapToEnterprise(EnterpriseDTO enterpriseDTO){
        Enterprise enterprise = new Enterprise();
        enterprise.setId(enterpriseDTO.getId());
        enterprise.setName(enterpriseDTO.getName());
        enterprise.setDescription(enterpriseDTO.getDescription());
        enterprise.setEmployees(enterpriseDTO.getEmployees());
        return enterprise;
    }

    public EnterpriseDTO mapToEnterpriseDTO(Enterprise enterprise){
        EnterpriseDTO enterpriseDTO = new EnterpriseDTO();
        enterpriseDTO.setId(enterprise.getId());
        enterpriseDTO.setName(enterprise.getName());
        enterpriseDTO.setDescription(enterprise.getDescription());
        enterpriseDTO.setEmployees(enterprise.getEmployees());
        return enterpriseDTO;
    }
}
