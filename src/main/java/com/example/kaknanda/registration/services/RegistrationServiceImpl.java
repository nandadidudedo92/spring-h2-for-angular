package com.example.kaknanda.registration.services;

import com.example.kaknanda.registration.entities.User;
import com.example.kaknanda.registration.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) throws Exception {

            if (user.getEmail() != null) {
                User oldUser = findEmail(user.getEmail());
                if (oldUser != null) {
                    throw new Exception("Email: "+user.getEmail()+" already Exist");
                }
            }
        User newUser =  userRepository.save(user);
        return newUser;
    }



    User findEmail(String email) {

        return userRepository.findByEmail(email);
    }
}
