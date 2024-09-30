package org.cris.rest.employability.services.impl;

import org.cris.rest.employability.models.dtos.UserDTO;
import org.cris.rest.employability.models.entities.UserData;
import org.cris.rest.employability.repositories.UserDataRepository;
import org.cris.rest.employability.services.UserDataService;
import org.cris.rest.employability.services.mappers.UserDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDataServiceImpl implements UserDataService {

    private UserDataRepository userDataRepository;
    private UserDataMapper userDataMapper;
    @Autowired
    public UserDataServiceImpl(UserDataRepository userDataRepository, UserDataMapper userDataMapper){
        this.userDataRepository = userDataRepository;
        this.userDataMapper = userDataMapper;
    }
    @Override
    public List<UserDTO> getAllUsers() {
        List<UserData> userData = userDataRepository.findAll();
        return userData.stream().map(userData1 -> userDataMapper.mapToUserDTO(userData1)).toList();
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<UserData> userData = userDataRepository.findById(id);
        return userData.map(userDataMapper::mapToUserDTO).orElse(null);

    }

    @Override
    public UserDTO getByUserPersonalId(String personalId) {
        Optional<UserData> userData = userDataRepository.findByPersonalId(personalId);
        return userData.map(userDataMapper::mapToUserDTO).orElse(null);
    }
}
