package com.enomy.controller;

import com.enomy.dao.UserDAO;
import com.enomy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        Model model,
        HttpSession session) {

        UserDAO userDAO = new UserDAO();
        User user = userDAO.validate(username, password);

        if (user != null) {
            session.setAttribute("loggedInUser", user);

            // 🔁 Redirect based on role
            if ("admin".equalsIgnoreCase(user.getRole())) {
                return "redirect:/admin/dashboard";
            } else {
            	System.out.println("redirecting to profile");
                return "redirect:/profile";
            }
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
