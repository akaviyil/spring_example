package com.ults.demo.app.controller;

import com.ults.demo.app.bean.Error;
import com.ults.demo.app.bean.LoginRequest;
import com.ults.demo.app.bean.RegistrationRequest;
import com.ults.demo.app.bean.Response;
import com.ults.demo.app.service.LoginService;
import com.ults.demo.app.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth/")
public class UserRegistrationController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RegistrationService registrationService;

    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

    private static final String USER_EXIST_ERROR = "User already exists, please select a new user name";
    private static final String LOGIN_FAILED_ERROR = "Invalid User credentials";


    @PostMapping(path = "/register", produces = "application/json")
    public ResponseEntity registerHandler(@Valid @RequestBody RegistrationRequest registration) {
        logger.info("Request data: {} ", registration);
        Response response = null;
        if(!registrationService.checkUserExists(registration.getName())) {
            response = registrationService.registerUser(registration);
        }else {
            response = new Response();
            var error = new Error();
            error.setErrorCode("10003");
            error.setErrorMessage(USER_EXIST_ERROR);
            response.setError(error);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity loginHandler(@Valid @RequestBody LoginRequest loginRequest) {
        logger.info("Request data:  {} ", loginRequest);
        var response = loginService.validateUser(loginRequest);
        if (response == null) {
            response = new Response();
            var error = new Error();
            error.setErrorCode("10000");
            error.setErrorMessage(LOGIN_FAILED_ERROR);
            response.setError(error);
        }
        return ResponseEntity.ok(response);
    }

}
