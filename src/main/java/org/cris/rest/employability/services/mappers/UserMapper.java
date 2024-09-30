package org.cris.rest.employability.services.mappers;

import org.cris.rest.employability.models.dtos.UserDTO;
import org.cris.rest.employability.models.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User mapToUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public UserDTO mapToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
