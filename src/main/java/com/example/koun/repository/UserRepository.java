package com.example.koun.repository;


import com.example.koun.domain.User;

import java.util.List;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByEmail(String userEmail);

    Optional<User> findByEmailAndProvider(String userEmail, String provider);



}
