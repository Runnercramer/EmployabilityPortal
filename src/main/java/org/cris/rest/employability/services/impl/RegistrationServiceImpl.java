package org.cris.rest.employability.services.impl;

import org.cris.rest.employability.models.dtos.UserDTO;
import org.cris.rest.employability.models.entities.User;
import org.cris.rest.employability.repositories.UserDataRepository;
import org.cris.rest.employability.repositories.UserRepository;
import org.cris.rest.employability.services.RegistrationService;
import org.cris.rest.employability.services.mappers.UserDataMapper;
import org.cris.rest.employability.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private UserRepository userRepository;
    private UserDataRepository userDataRepository;
    private UserMapper userMapper;
    private UserDataMapper userDataMapper;
    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository, UserMapper userMapper, UserDataMapper userDataMapper, UserDataRepository userDataRepository){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userDataMapper = userDataMapper;
        this.userDataRepository = userDataRepository;
    }
    @Override
    public String createUser(UserDTO user) {
        try{
            User response = userRepository.save(userMapper.mapToUser(user));
            userDataRepository.save(userDataMapper.mapToUserData(user));
            return response.getId().toString();
        } catch (Exception ex) {
            return null;
        }
    }
}
