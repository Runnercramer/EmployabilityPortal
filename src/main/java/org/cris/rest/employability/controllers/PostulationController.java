package org.cris.rest.employability.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.cris.rest.employability.models.dtos.GenericResponse;
import org.cris.rest.employability.models.dtos.PostulationDTO;
import org.cris.rest.employability.services.PostulationService;
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

@RestController("postulations")
public class PostulationController {

    private PostulationService postulationService;

    @Autowired
    public PostulationController(PostulationService postulationService) {
        this.postulationService = postulationService;
    }

    @PostMapping("create")
    public ResponseEntity<Object> createPostulation(@RequestBody PostulationDTO postulationData, HttpServletRequest request) {
        String response = this.postulationService.createPostulation(postulationData);
        if (response != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(request.getRequestURL() + "/" + response));
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
        GenericResponse genericResponse = new GenericResponse("Postulation was not created",
                400,
                "Postulation couldn't be created");
        return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<Object> getAllPostulations(){
        List<PostulationDTO> postulations = this.postulationService.getPostulations();
        if (!postulations.isEmpty()) return new ResponseEntity<>(postulations, HttpStatus.OK);
        else {
            GenericResponse genericResponse = new GenericResponse("Postulations were not found",
                    404,
                    "Postulations couldn't be found");
            return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> findPostulationById(@PathVariable String id){
        PostulationDTO postulationDTO = this.postulationService.findPostulationById(id);
        if(postulationDTO != null) return new ResponseEntity<>(postulationDTO, HttpStatus.OK);
        GenericResponse genericResponse = new GenericResponse("Postulation was not found",
                404,
                "Postulation couldn't be found by this " + id + " id");
        return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Object> updatePostulation(@RequestBody PostulationDTO postulationDTO,
                                                    @PathVariable String id,
                                                    HttpServletRequest request){
        String response = this.postulationService.updatePostulation(postulationDTO, id);
        if (response != null){
            String location = request.getRequestURL() + "/" + response;
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(location));
            return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        }
        GenericResponse genericResponse = new GenericResponse("Postulation was not updated",
                404,
                "Postulation by this " + id + " id couldn't be updated");
        return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
    }

    @GetMapping("applicants/{id}")
    public ResponseEntity<Object> findByApplicantId(@PathVariable String id){
        List<PostulationDTO> postulationDTOS = this.postulationService.findByApplicantId(id);
        if (postulationDTOS != null){
            return new ResponseEntity<>(postulationDTOS, HttpStatus.OK);
        }
        GenericResponse genericResponse = new GenericResponse("Postulations were not found",
                404,
                "Postulation by this " + id + " applicant id couldn't be found");
        return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
    }

}
