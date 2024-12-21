package com.TokenGeneration.SpringBootGeneration.service;

import com.TokenGeneration.SpringBootGeneration.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    
    void saveUser(User user);
      
    User getUserBYID(long id);

    void deleteUserById(long id);

    User getUserByEmailAndPassword(String email, String password);// Add this method


    List<User> searchUsers(String query);




}
