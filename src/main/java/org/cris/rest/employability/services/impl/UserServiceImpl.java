package org.cris.rest.employability.services.impl;

import org.cris.rest.employability.models.dtos.UserDTO;
import org.cris.rest.employability.models.entities.User;
import org.cris.rest.employability.repositories.UserRepository;
import org.cris.rest.employability.services.SecurityService;
import org.cris.rest.employability.services.UserService;
import org.cris.rest.employability.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setUsername(this.securityService.obfuscateData(user.get().getUsername(), false));
            user.get().setPassword(this.securityService.obfuscateData(user.get().getPassword(), true));
            return this.userMapper.mapToUserDTO(user.get());
        }
        return null;
    }
}
