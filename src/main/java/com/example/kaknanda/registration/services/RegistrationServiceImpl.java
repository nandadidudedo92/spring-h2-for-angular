package com.example.kaknanda.registration.services;

import com.example.kaknanda.registration.entities.User;
import com.example.kaknanda.registration.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    @Override
    public User saveUser(User user) throws Exception {

            if (user.getEmail() != null) {
                User oldUser = findEmail(user.getEmail());
                if (oldUser != null) {
                    throw new Exception("Email: "+user.getEmail()+" already Exist");
                }
            }
        User newUser =  registrationRepository.save(user);
        return newUser;
    }

    @Override
    public User doLogin(User user) throws Exception {
        if (user.getUserName().equals("")|| user.getPassword().equals("")) {
            throw new Exception("Username / Password cannot be null");
        }
        User logUser = findUser(user.getUserName(), user.getPassword());

        if(logUser == null) {
            throw new Exception("Username / Password is Incorrect");
        }
        return logUser;
    }

    User findUser(String username, String password) {
        return registrationRepository.findByUserNameAndPassword(username, password);

    }

    User findEmail(String email) {

        return registrationRepository.findByEmail(email);
    }
}
