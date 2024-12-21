package com.TokenGeneration.SpringBootGeneration.repository;

import com.TokenGeneration.SpringBootGeneration.model.Admin;
import com.TokenGeneration.SpringBootGeneration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

    //User findByEmail(String email);

    Admin findByEmailAndPassword(String email, String password);
}
