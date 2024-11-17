package org.cris.rest.employability.services;

import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface JWTService {
    String createToken(Long subject) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException;
    Claims validateToken(String jwt) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException;
}
