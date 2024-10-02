package org.cris.rest.employability.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.cris.rest.employability.models.dtos.GenericResponse;
import org.cris.rest.employability.models.dtos.UserDTO;
import org.cris.rest.employability.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("registration")
public class RegistrationController {
    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        String response = registrationService.createUser(userDTO);
        if (response != null) {
            String location = request.getRequestURL().toString() + "/" + response;
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(location));
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } else {
            GenericResponse genericResponse = new GenericResponse("Error creating user",
                    400,
                    "Couldn't create a new user");
            return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
