package com.TokenGeneration.SpringBootGeneration.controller;

import com.TokenGeneration.SpringBootGeneration.model.Admin;
import com.TokenGeneration.SpringBootGeneration.model.User;
import com.TokenGeneration.SpringBootGeneration.repository.UserRepository;
import com.TokenGeneration.SpringBootGeneration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    // Predefined admin credentials
    private final String ADMIN_EMAIL = "admin@gmail.com";
    private final String ADMIN_PASSWORD = "admin123";

/*
    private final String COMPANY_EMAIL_1 = "company1@gmail.com";
    private final String COMPANY_PASSWORD_2 = "company1123";


    private final String COMPANY_EMAIL_1 = "company2@gmail.com";
    private final String COMPANY_PASSWORD_2 = "company2123";

*/


    @Autowired
    private UserService ser;


    @GetMapping("/")
    public String home(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return"index.html";
    }


    @GetMapping("/list")
    public String viewHomePage(Model model){
        model.addAttribute("listUsers",ser.getAllUsers());
        return "admin_actual_dashboard.html";
    }


    @GetMapping("/clint_list")
    public String clintHomePage(Model model){
        model.addAttribute("listUsers",ser.getAllUsers());
        return "client_actual_dashboard.html";
    }


    @PostMapping("/saveUpdate")
    public String saveUpdate(@ModelAttribute("user") User user){
        //save employee to database
        System.out.println("The data is stored");
        ser.saveUser(user);
        return "redirect:/list";
    }


    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user){
        //save employee to database
        System.out.println("The data is stored");
        ser.saveUser(user);
        return "redirect:/admincomp";
    }


    @GetMapping("/showFormUpdate/{id}")
    public String showFormUpdate(@PathVariable (value = "id") long id,Model model){
        //get employee from the service
        User user = ser.getUserBYID(id);
        //set employee as a model attribute to pre-populate the form
        model.addAttribute("user",user);
        return "update_actual";
    }


    @GetMapping("/deleteForm/{id}")
    public String deleteForm(@PathVariable(value = "id") long id){
        // call delete employee method
        this.ser.deleteUserById(id);
        return "redirect:/list";
    }


    @GetMapping("/clint_list/{id}")
    public String clientHomePage(@PathVariable(value = "id") long id, Model model){
        User user = ser.getUserBYID(id);
        model.addAttribute("user", user);
        return "client_actual_dashboard"; // Redirect to client page
    }


    @PostMapping("/adminLogin")
    public String adminLogin(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             Model model) {
        if (ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password)) {
            return "redirect:/list"; // Admin successfully logged in
        } else {
            model.addAttribute("error", "Invalid admin credentials.");
            return "/admincomp"; // Reload login page with error
        }
    }


    /**
     @PostMapping("/userLogin")
     public String userLogin(@RequestParam("email") String email,
     @RequestParam("password") String password,
     Model model) {
     // Authenticate the user
     User user = ser.getUserByEmailAndPassword(email, password);
     if (user != null) {
     return "redirect:/"; // User successfully logged in
     } else {
     model.addAttribute("error", "Invalid user email or password");
     return "admincomp"; // Reload login page with error
     }
     }
     */


    @PostMapping("/userLogin")
    public String userLogin(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            Model model) {
        // Authenticate the user
        User user = ser.getUserByEmailAndPassword(email, password);
        if (user != null) {
            model.addAttribute("user", user); // Pass only the logged-in user's data
            return "client_actual_dashboard"; // Redirect to a client-specific page
        } else {
            model.addAttribute("error", "Invalid user email or password");
            return "admincomp"; // Reload login page with error
        }
    }




    // Search method to handle queries
    @GetMapping("/search")
    public String searchUsers(@RequestParam("query") String query, Model model) {
        List<User> filteredUsers = ser.searchUsers(query);
        model.addAttribute("listUsers", filteredUsers);
        return "admin_actual_dashboard"; // Redirect to the dashboard with filtered data
    }




}
