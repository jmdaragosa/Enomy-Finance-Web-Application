package com.enomy.controller;

import com.enomy.dao.AdminDAO;
import com.enomy.dao.CurrencyQuoteDAO;
import com.enomy.dao.InvestmentPlanDAO;
import com.enomy.model.CurrencyQuote;
import com.enomy.model.InvestmentPlan;
import com.enomy.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminDAO adminDAO = new AdminDAO();

    @GetMapping("/dashboard")
    public String showAdminDashboard(HttpSession session, Model model) {
        User loggedIn = (User) session.getAttribute("loggedInUser");
        if (loggedIn == null || !"admin".equals(loggedIn.getRole())) {
            return "redirect:/login";
        }

        List<User> users = adminDAO.getAllUsers();
        model.addAttribute("users", users);
        return "adminDashboard";
    }

    @GetMapping("/user/{id}")
    public String viewUserDetails(@PathVariable int id, Model model, HttpSession session) {
        User loggedIn = (User) session.getAttribute("loggedInUser");
        if (loggedIn == null || !"admin".equals(loggedIn.getRole())) {
            return "redirect:/login";
        }

        try {
            User user = adminDAO.getUserById(id);

            CurrencyQuoteDAO quoteDAO = new CurrencyQuoteDAO(); // assumes similar no-arg constructor
            List<CurrencyQuote> conversions = quoteDAO.getQuotesByUserId(id);

            InvestmentPlanDAO investmentDAO = new InvestmentPlanDAO();
            List<InvestmentPlan> investments = investmentDAO.getQuotesByUserId(id);

            model.addAttribute("user", user);
            model.addAttribute("currencyQuotes", conversions);
            model.addAttribute("investmentQuotes", investments);

        } catch (Exception e) {
            e.printStackTrace();
            // If DB fails, send empty lists to avoid breaking the view
            model.addAttribute("currencyQuotes", List.of());
            model.addAttribute("investmentQuotes", List.of());
        }

        return "adminUserProfile";
    }
}
