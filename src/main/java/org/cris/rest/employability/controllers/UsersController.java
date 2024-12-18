package org.cris.rest.employability.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.cris.rest.employability.models.dtos.GenericResponse;
import org.cris.rest.employability.models.dtos.UserDTO;
import org.cris.rest.employability.services.RegistrationService;
import org.cris.rest.employability.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {

    private UserDataService userDataService;
    private RegistrationService registrationService;
    private static final String DATA_NOT_FOUND = "Data Not Found";

    @Autowired
    public UsersController(UserDataService userDataService, RegistrationService registrationService) {
        this.userDataService = userDataService;
        this.registrationService = registrationService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUsers(@RequestParam(required = false) Long id) {
        if (id != null) {
            UserDTO userDTO = userDataService.getUserById(id);
            if (userDTO != null) {
                return new ResponseEntity<>(userDTO, HttpStatus.OK);
            } else {
                GenericResponse genericResponse = new GenericResponse(DATA_NOT_FOUND,
                        404,
                        "User not found by this " + id + " id");
                return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
            }
        } else {
            List<UserDTO> users = userDataService.getAllUsers();
            if (users != null && !users.isEmpty()) {
                return new ResponseEntity<>(users, HttpStatus.OK);
            } else {
                GenericResponse genericResponse = new GenericResponse(DATA_NOT_FOUND,
                        404,
                        "Users not found");
                return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
            }
        }
    }

    @GetMapping(value = "/{personalId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserByPersonalId(@PathVariable String personalId) {
        UserDTO userDTO = userDataService.getUserByPersonalId(personalId);
        if (userDTO != null) return new ResponseEntity<>(userDTO, HttpStatus.OK);
        else {
            GenericResponse genericResponse = new GenericResponse(DATA_NOT_FOUND,
                    404,
                    "User not found by this " + personalId + " personal id");
            return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "registration/create", produces = MediaType.APPLICATION_JSON_VALUE)
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
