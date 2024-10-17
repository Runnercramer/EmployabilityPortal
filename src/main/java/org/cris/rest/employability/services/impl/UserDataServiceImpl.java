package org.cris.rest.employability.services.impl;

import org.cris.rest.employability.models.dtos.UserDTO;
import org.cris.rest.employability.models.entities.UserData;
import org.cris.rest.employability.repositories.UserDataRepository;
import org.cris.rest.employability.services.UserDataService;
import org.cris.rest.employability.services.UserService;
import org.cris.rest.employability.services.mappers.UserDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDataServiceImpl implements UserDataService {

    private UserDataRepository userDataRepository;
    private UserDataMapper userDataMapper;
    private UserService userService;
    @Autowired
    public UserDataServiceImpl(UserDataRepository userDataRepository, UserDataMapper userDataMapper, UserService userService){
        this.userDataRepository = userDataRepository;
        this.userDataMapper = userDataMapper;
        this.userService = userService;
    }
    @Override
    public List<UserDTO> getAllUsers() {
        List<UserData> usersData = userDataRepository.findAll();
        List<UserDTO> response = new ArrayList<>();
        for(UserData userData : usersData){
            UserDTO userDTO = this.userService.getUserDTOById(userData.getId());
            UserDTO finalUserDTO = this.userDataMapper.mapToUserDTO(userData);
            finalUserDTO.setUsername(userDTO.getUsername());
            finalUserDTO.setPassword(userDTO.getPassword());
            response.add(finalUserDTO);
        }
        return response;
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<UserData> userData = userDataRepository.findById(id);
        return userData.map(userDataMapper::mapToUserDTO).orElse(null);

    }

    @Override
    public UserDTO getUserByPersonalId(String personalId) {
        Optional<UserData> userData = userDataRepository.findByPersonalId(personalId);
        if (userData.isPresent()){
            UserDTO userDTO = this.userService.getUserDTOById(userData.get().getId());
            UserDTO finalUserDTO = this.userDataMapper.mapToUserDTO(userData.get());
            finalUserDTO.setUsername(userDTO.getUsername());
            finalUserDTO.setPassword(userDTO.getPassword());
            return finalUserDTO;
        }
        return null;
    }
}
