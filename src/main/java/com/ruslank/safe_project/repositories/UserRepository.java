package com.ruslank.safe_project.repositories;

import com.ruslank.safe_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    @Query("SELECT u FROM User u WHERE u.username = :username")
    public Optional<User> findByUsername(String username);
}
