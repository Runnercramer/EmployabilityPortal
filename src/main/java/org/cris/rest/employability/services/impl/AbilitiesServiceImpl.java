package org.cris.rest.employability.services.impl;

import org.cris.rest.employability.models.entities.Ability;
import org.cris.rest.employability.repositories.AbilityRepository;
import org.cris.rest.employability.services.AbilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AbilitiesServiceImpl implements AbilitiesService {

    private AbilityRepository abilityRepository;

    @Autowired
    public AbilitiesServiceImpl(AbilityRepository abilityRepository) {
        this.abilityRepository = abilityRepository;
    }

    @Override
    public List<String> saveAbilities(List<Map<String, String>> abilities) {
        List<String> response = new ArrayList<>();

        abilities.forEach(abilityMap -> {
            abilityMap.forEach((name, description) -> {
                Ability entity = new Ability();
                String id = UUID.randomUUID().toString();
                entity.setId(id);
                entity.setName(name);
                entity.setDescription(description);

                abilityRepository.save(entity);

                response.add(id);
            });
        });

        return response;
    }


    @Override
    public List<Map<String, String>> getAbilitiesByIds(List<String> ids) {
        List<Ability> abilities = this.abilityRepository.findAllById(ids);
        List<Map<String, String>> response = new ArrayList<>();
        abilities.forEach(ability -> {
            Map<String, String> map = new HashMap<>();
            map.put(ability.getName(), ability.getDescription());
            response.add(map);
        });
        return response;
    }
}
