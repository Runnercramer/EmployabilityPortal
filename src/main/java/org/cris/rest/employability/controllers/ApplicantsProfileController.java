package org.cris.rest.employability.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.cris.rest.employability.models.dtos.GenericResponse;
import org.cris.rest.employability.models.dtos.ProfileDTO;
import org.cris.rest.employability.services.ProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("applicants/profile")
public class ApplicantsProfileController {

    private ProfilesService profilesService;

    @Autowired
    public ApplicantsProfileController(ProfilesService profilesService) {
        this.profilesService = profilesService;
    }

    @PostMapping("create")
    public ResponseEntity<Object> createProfile(@RequestBody ProfileDTO profileDTO, HttpServletRequest request) {
        String id = this.profilesService.createProfile(profileDTO);
        if (id != null && !id.isEmpty()) {
            String location = request.getRequestURL().toString() + "/" + id;
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(location));
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } else {
            GenericResponse genericResponse = new GenericResponse("Error creating profile",
                    400,
                    "Couldn't create a new profile");
            return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getApplicantProfile(@PathVariable String id) {
        ProfileDTO profileDTO = this.profilesService.getProfileById(id);
        if (profileDTO != null) {
            return new ResponseEntity<>(profileDTO, HttpStatus.OK);
        }
        GenericResponse genericResponse = new GenericResponse("Data not found",
                404,
                "There is not any profile identified by this " + id + " id");
        return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Object> updateApplicantProfile(@PathVariable String id,
                                                         @RequestBody ProfileDTO profileDTO,
                                                         HttpServletRequest request) {
        String response = this.profilesService.updateProfile(id, profileDTO);
        if (response != null) {
            String location = request.getRequestURL().toString() + "/" + response;
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(location));
            return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        }
        GenericResponse genericResponse = new GenericResponse("Profile not updated",
                404,
                "There is not any profile identified by this " + id + " id");
        return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
    }
}
