package com.TokenGeneration.SpringBootGeneration.service;

import com.TokenGeneration.SpringBootGeneration.model.Admin;

public interface AdminService {

    void saveAdmin(Admin admin);

    Admin getAdminBYID(long id);


    Admin getAdminByEmailAndPassword(String email, String password);

}
