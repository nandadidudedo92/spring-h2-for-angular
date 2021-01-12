package com.example.kaknanda.registration.controllers;

import com.example.kaknanda.registration.entities.User;
import com.example.kaknanda.registration.response.CommonResponse;
import com.example.kaknanda.registration.response.CommonResponseGenerator;
import com.example.kaknanda.registration.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    CommonResponseGenerator comGen;

    @PostMapping(value = "register")
    public CommonResponse<User> registerUser(@RequestBody User user) {

        try {
            User newUser = registrationService.saveUser(user);
            return comGen.commonSuccessResponse(newUser,"Registration Success");
        } catch (Exception e){
            return comGen.commonFailedError(e);
        }

    }

    @PostMapping(value = "login")
    public CommonResponse<User> login(@RequestBody User user) {
        try {
            User newUser = registrationService.doLogin(user);
            return comGen.commonSuccessResponse(newUser,"Login Success");
        } catch (Exception e){
            return comGen.commonFailedError(e);
        }
    }

}
