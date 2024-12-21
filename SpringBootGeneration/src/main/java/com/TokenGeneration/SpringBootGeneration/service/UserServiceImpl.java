package com.TokenGeneration.SpringBootGeneration.service;

import com.TokenGeneration.SpringBootGeneration.model.User;
import com.TokenGeneration.SpringBootGeneration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;


    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }


    @Override
    public void saveUser(User user) {
        this.repo.save(user);
    }
    

    @Override
    public User getUserBYID(long id) {
        Optional<User> optional = repo.findById(id);
        User user = null;
        if(optional.isPresent()){
              user = optional.get();
        }else{
            throw new RuntimeException("User not found for id ::"+id);
        }
        return user;
    }


    @Override
    public void deleteUserById(long id) {
        this.repo.deleteById(id);
    }


   // @Override
   // public User findByEmail(String email) {
    //    return repo.findByEmail(email);
   // }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        //Logic to find the user by email and password
        return repo.findByEmailAndPassword(email, password);
    }


       @Override
    public List<User> searchUsers(String query) {
        return repo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrProblemContainingIgnoreCase(query, query, query,query);
    }





}
