package com.example.kaknanda.registration.services;

import com.example.kaknanda.registration.entities.User;
import org.springframework.stereotype.Component;

@Component
public interface LoginService {

    User doLogin(User user) throws  Exception;
}
