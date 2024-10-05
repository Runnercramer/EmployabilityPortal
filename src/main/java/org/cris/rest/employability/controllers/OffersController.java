package org.cris.rest.employability.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.cris.rest.employability.models.dtos.GenericResponse;
import org.cris.rest.employability.models.dtos.OfferDTO;
import org.cris.rest.employability.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController("offers")
public class OffersController {

    private OfferService offerService;

    @Autowired
    public OffersController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllOffers() {
        List<OfferDTO> offerDTOS = this.offerService.getAllOffers();
        if (offerDTOS != null && !offerDTOS.isEmpty()) return new ResponseEntity<>(offerDTOS, HttpStatus.OK);
        GenericResponse genericResponse = new GenericResponse("Offers were not found",
                404,
                "Offer couldn't be found");
        return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
    }

    @PostMapping("create")
    public ResponseEntity<Object> saveOffer(@RequestBody OfferDTO offerDTO, HttpServletRequest request) {
        String response = this.offerService.createOffer(offerDTO);
        if (response != null) {
            String location = request.getRequestURL().toString() + "/" + response;
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(location));
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
        GenericResponse genericResponse = new GenericResponse("Offer was not created",
                400,
                "Offer couldn't be created");
        return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> findOfferById(@PathVariable String id) {
        OfferDTO offerDTO = this.offerService.getOfferById(id);
        if (offerDTO != null) return new ResponseEntity<>(offerDTO, HttpStatus.OK);
        GenericResponse genericResponse = new GenericResponse("Offer was not found",
                404,
                "Offer couldn't be found by this " + id + " id");
        return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
    }

    @GetMapping("creator/{createdBy}")
    public ResponseEntity<Object> findOfferByCreatedBy(@PathVariable String createdBy) {
        List<OfferDTO> offerDTOS = this.offerService.getOffersCreatedBy(createdBy);
        if (offerDTOS != null && !offerDTOS.isEmpty()) return new ResponseEntity<>(offerDTOS, HttpStatus.OK);
        GenericResponse genericResponse = new GenericResponse("Offers were not found",
                404,
                "Offers couldn't be found created by this " + createdBy + " personal id");
        return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Object> updateOffer(@PathVariable String id,
                                              @RequestBody OfferDTO offerDTO,
                                              HttpServletRequest request) {
        return null;
    }
}
