package com.TokenGeneration.SpringBootGeneration.service;

import com.TokenGeneration.SpringBootGeneration.model.Admin;
import com.TokenGeneration.SpringBootGeneration.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{


    @Autowired
    private AdminRepository adminrepo;

    @Override
    public void saveAdmin(Admin admin) {
        this.adminrepo.save(admin);
    }



    @Override
    public Admin getAdminBYID(long id) {
        Optional<Admin> optional = adminrepo.findById(id);
        Admin admin = null;
        if(optional.isPresent()){
            admin = optional.get();
        }else{
            throw new RuntimeException("User not found for id ::"+id);
        }
        return admin;
    }



    @Override
    public Admin getAdminByEmailAndPassword(String email, String password) {

            //Logic to find the user by email and password
            return adminrepo.findByEmailAndPassword(email, password);
    }




}
