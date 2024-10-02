package org.cris.rest.employability.services;

import org.cris.rest.employability.models.dtos.UserDTO;

public interface UserService {
    UserDTO getUserDTOById(Long id);
}
