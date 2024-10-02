package org.cris.rest.employability.services.impl;

import org.cris.rest.employability.models.dtos.UserDTO;
import org.cris.rest.employability.models.entities.User;
import org.cris.rest.employability.repositories.UserRepository;
import org.cris.rest.employability.services.SecurityService;
import org.cris.rest.employability.services.UserService;
import org.cris.rest.employability.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private SecurityService securityService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, SecurityService securityService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.securityService = securityService;
    }

    @Override
    public UserDTO getUserDTOById(Long id) {
        User user = this.userRepository.getReferenceById(id);
        user.setUsername(this.securityService.obfuscateData(user.getUsername(), false));
        user.setPassword(this.securityService.obfuscateData(user.getPassword(), true));
        return this.userMapper.mapToUserDTO(user);
    }
}
