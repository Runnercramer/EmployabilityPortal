package org.cris.rest.employability;

import org.cris.rest.employability.controllers.UsersController;
import org.cris.rest.employability.services.RegistrationService;
import org.cris.rest.employability.services.UserDataService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UsersControllerTest {

    @InjectMocks
    private UsersController usersController;

    @Mock
    private UserDataService userDataService;

    @Mock
    private RegistrationService registrationService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFailedGetUsers(){
        ResponseEntity<Object> response = this.usersController.getUsers(null);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
