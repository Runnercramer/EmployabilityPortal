package org.cris.rest.employability.services;

import org.cris.rest.employability.models.dtos.UserDTO;

import java.util.List;

public interface UserDataService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO getByUserPersonalId(String personalId);
}
