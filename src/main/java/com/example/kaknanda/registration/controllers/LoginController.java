package com.example.kaknanda.registration.controllers;

import com.example.kaknanda.registration.entities.User;
import com.example.kaknanda.registration.response.CommonResponse;
import com.example.kaknanda.registration.response.CommonResponseGenerator;
import com.example.kaknanda.registration.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    CommonResponseGenerator comGen;


    @PostMapping(value = "login")
    public CommonResponse<User> login(@RequestBody User user) {
        try {
            User newUser = loginService.doLogin(user);
            return comGen.commonSuccessResponse(newUser,"Login Success");
        } catch (Exception e){
            return comGen.commonFailedError(e);
        }
    }

}
