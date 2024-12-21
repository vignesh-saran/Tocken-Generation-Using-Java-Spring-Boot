package com.TokenGeneration.SpringBootGeneration.repository;

import com.TokenGeneration.SpringBootGeneration.model.Admin;
import com.TokenGeneration.SpringBootGeneration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);


    // Custom query to search by name, email, or problem description
    List<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrProblemContainingIgnoreCase(
            String name, String email, String phone, String problem
    );


}


