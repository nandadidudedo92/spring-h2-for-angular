package com.example.kaknanda.registration.services;

import com.example.kaknanda.registration.entities.User;
import com.example.kaknanda.registration.repositories.RegistrationRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

        String token = getJWTToken(user.getUserName());
        logUser.setToken(token);

        return logUser;
    }

    private String getJWTToken(String username) {
        String secretKey = "kakNanda";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    User findUser(String username, String password) {
        return registrationRepository.findByUserNameAndPassword(username, password);

    }

    User findEmail(String email) {

        return registrationRepository.findByEmail(email);
    }
}
