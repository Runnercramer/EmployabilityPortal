package org.cris.rest.employability.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cris.rest.employability.services.JWTService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    public JwtAuthFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String headerAuthorization = request.getHeader("Authorization");
        String jwt = null;
        if (headerAuthorization != null && headerAuthorization.startsWith("Bearer ")) {
            jwt = headerAuthorization.substring(7);
        }

        if (jwt != null) {
            try {
                Claims claims = jwtService.validateToken(jwt);
                if (new Date().after(claims.getExpiration())) {
                    //TODO: Refrescar un nuevo JWT
                }
                // Aquí puedes establecer el contexto de seguridad
                // por ejemplo, utilizando un objeto de autenticación
                // SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Inválido");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
