package com.example.kaknanda.registration.repositories;

import com.example.kaknanda.registration.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);

    User findByUserNameAndPassword(String username, String password);

}
