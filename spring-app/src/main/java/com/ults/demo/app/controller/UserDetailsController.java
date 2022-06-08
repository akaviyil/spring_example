package com.ults.demo.app.controller;

import com.ults.demo.app.bean.Error;
import com.ults.demo.app.bean.Response;
import com.ults.demo.app.service.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user/")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsController.class);
    private static final String INVALID_TOKEN_ERROR = "Invalid Token";

    @GetMapping(path = "/me/{token}", produces = "application/json")
    public ResponseEntity registerHandler(@PathVariable String token) {
        logger.info("Request data: {} ", token);
        Response response = userDetailsService.findUserDetails(token);
        if (response == null) {
            response = new Response();
            var error = new Error();
            error.setErrorCode("10001");
            error.setErrorMessage(INVALID_TOKEN_ERROR);
            response.setError(error);
        }
        return ResponseEntity.ok(response);
    }
}
