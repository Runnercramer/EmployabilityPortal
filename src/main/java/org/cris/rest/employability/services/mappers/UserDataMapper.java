package org.cris.rest.employability.services.mappers;

import org.cris.rest.employability.models.dtos.UserDTO;
import org.cris.rest.employability.models.entities.UserData;
import org.springframework.stereotype.Service;

@Service
public class UserDataMapper {

    public UserData mapToUserData(UserDTO userDTO){
        UserData userData = new UserData();
        userData.setId(userDTO.getId());
        userData.setName(userDTO.getName());
        userData.setLastName(userDTO.getLastName());
        userData.setPersonalId(userDTO.getPersonalId());
        userData.setRole(userDTO.getRole());
        userData.setEmail(userDTO.getEmail());
        return userData;
    }

    public UserDTO mapToUserDTO(UserData userData){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userData.getId());
        userDTO.setName(userData.getName());
        userDTO.setLastName(userData.getLastName());
        userDTO.setPersonalId(userData.getPersonalId());
        userDTO.setRole(userData.getRole());
        userDTO.setEmail(userData.getEmail());
        return userDTO;
    }
}
