package com.example.demo.repository;

import com.example.demo.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, String> {

    AuthUser findAuthUserByUsername(String username);

}
