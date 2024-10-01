package org.cris.rest.employability.controllers;

import org.cris.rest.employability.models.dtos.GenericResponse;
import org.cris.rest.employability.models.dtos.ProfileDTO;
import org.cris.rest.employability.services.ProfilesService;
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
@RequestMapping("applicants")
public class ApplicantsController {

    private ProfilesService profilesService;

    @Autowired
    public ApplicantsController(ProfilesService profilesService) {
        this.profilesService = profilesService;
    }

    @PostMapping
    @RequestMapping(value = "/profile/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createProfile(@RequestBody ProfileDTO profileDTO) {
        String location = profilesService.createProfile(profileDTO);
        if (location != null && !location.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(location));
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
        GenericResponse genericResponse = new GenericResponse("Error creating profile",
                400,
                "Couldn't create a new profile");
        return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);

    }
}
