package org.cris.rest.employability.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("applicants")
public class ApplicantsController {

    @PostMapping
    @RequestMapping(value = "/profile/create")
    public ResponseEntity<Object> createProfile(){return null;}
}
