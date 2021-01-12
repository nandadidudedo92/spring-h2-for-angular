package com.example.kaknanda.registration.services;

import com.example.kaknanda.registration.entities.User;
import com.example.kaknanda.registration.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepository userRepository;

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
        return userRepository.findByUserNameAndPassword(username, password);

    }
}
