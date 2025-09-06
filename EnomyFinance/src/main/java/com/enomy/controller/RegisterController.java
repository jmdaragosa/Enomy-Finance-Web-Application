package com.enomy.controller;

import com.enomy.dao.UserDAO;
import com.enomy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    private final UserDAO userDAO = new UserDAO();

    // Show the registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // resolves to register.jsp
    }

    // Handle form submission
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") User user, Model model) {

        // Set default role to client (prevent abuse)
        user.setRole("user");

        boolean success = userDAO.registerUser(user);

        if (success) {
            model.addAttribute("message", "✅ Registration successful. You can now log in.");
            return "login"; // redirect to login.jsp
        } else {
            model.addAttribute("error", "❌ Registration failed. Username may already exist.");
            return "register"; // show form again
        }
    }
}
