package org.cris.rest.employability.services.impl;

import org.cris.rest.employability.models.dtos.EnterpriseDTO;
import org.cris.rest.employability.models.entities.Enterprise;
import org.cris.rest.employability.repositories.EnterpriseRepository;
import org.cris.rest.employability.services.EnterpriseService;
import org.cris.rest.employability.services.mappers.EnterpriseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    private EnterpriseRepository enterpriseRepository;
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    public EnterpriseServiceImpl(EnterpriseRepository enterpriseRepository, EnterpriseMapper enterpriseMapper){
        this.enterpriseRepository = enterpriseRepository;
        this.enterpriseMapper = enterpriseMapper;
    }
    @Override
    public String saveEnterprise(EnterpriseDTO enterpriseDTO) {
        Enterprise enterprise = this.enterpriseMapper.mapToEnterprise(enterpriseDTO);
        enterprise.setId(UUID.randomUUID().toString());
        this.enterpriseRepository.save(enterprise);
        return enterprise.getId();
    }

    @Override
    public EnterpriseDTO getEnterpriseById(String id) {
        Optional<Enterprise> enterprise = this.enterpriseRepository.findById(id);
        return enterprise.map(enterpriseMapper::mapToEnterpriseDTO).orElse(null);
    }
}
