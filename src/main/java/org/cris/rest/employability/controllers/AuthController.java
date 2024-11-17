package org.cris.rest.employability.controllers;

import org.cris.rest.employability.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JWTService jwtService;

    @Autowired
    public AuthController(JWTService jwtService){
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<Object> login(@RequestParam String username,
                                        @RequestParam String password) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        //TODO: Verificar si las credenciales corresponden con un suario creado
        String token = this.jwtService.createToken(1L);
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", token);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
