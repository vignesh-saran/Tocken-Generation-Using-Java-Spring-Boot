package com.TokenGeneration.SpringBootGeneration.controller;

import com.TokenGeneration.SpringBootGeneration.model.Admin;
import com.TokenGeneration.SpringBootGeneration.model.User;
import com.TokenGeneration.SpringBootGeneration.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminser;

    @PostMapping("/saveAdmin")
    public String saveAdmin(@ModelAttribute("admin") Admin admin) {
        // Save admin to database
        System.out.println("The data is stored");
        adminser.saveAdmin(admin);
        return "redirect:/list"; // Adjust the path as needed
    }

    @GetMapping("/admincomp")
    public String admincomp(Model model) {
        Admin admin = new Admin();
        model.addAttribute("admin",admin);
        return "company_query_form.html";
    }


    @PostMapping("/admincompLogin")
    public String userLogin(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            Model model) {
        // Authenticate the user
        Admin admin = adminser.getAdminByEmailAndPassword(email, password);
        if (admin != null) {
            return "redirect:/admincomp"; // Adjust the path as needed
        } else {
            model.addAttribute("error", "Invalid user email or password.");
            return "redirect:/"; // Redirect back to login page with error
        }
    }
}
