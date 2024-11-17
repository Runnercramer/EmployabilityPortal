package org.cris.rest.employability.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import org.cris.rest.employability.services.JWTService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Service
public class JWTServiceImpl implements JWTService {

    @Value("classpath:keys/emp.pem")
    private Resource privateKey;

    @Value("classpath:keys/pub_emp.pem")
    private Resource publicKey;

    @Override
    public String createToken(Long subject) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        JwtBuilder builder = Jwts.builder()
                .subject(String.valueOf(subject))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 3600000))
                .signWith(getPrivateKey());
        return builder.compact();
    }

    private PrivateKey getPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String privateKeyContent = privateKey.getContentAsString(StandardCharsets.UTF_8);
        privateKeyContent = privateKeyContent.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] encodedKey = Base64.getDecoder().decode(privateKeyContent);
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }

    @Override
    public Claims validateToken(String jwt) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Jws<Claims> jws = Jwts
                .parser()
                .verifyWith(getPublicKey())
                .build()
                .parseSignedClaims(jwt);
        return jws.getPayload();
    }

    private PublicKey getPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String publicKeyContent = publicKey.getContentAsString(StandardCharsets.UTF_8);
        publicKeyContent = publicKeyContent.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] encodedKey = Base64.getDecoder().decode(publicKeyContent);
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(encodedKey));
    }
}
