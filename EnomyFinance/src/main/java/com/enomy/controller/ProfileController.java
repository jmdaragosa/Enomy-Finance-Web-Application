package com.enomy.controller;

import com.enomy.dao.CurrencyQuoteDAO;
import com.enomy.dao.InvestmentPlanDAO;
import com.enomy.model.CurrencyQuote;
import com.enomy.model.InvestmentPlan;
import com.enomy.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {

    @RequestMapping("/profile")
    public String showProfile(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }
        System.out.println("outside try");
        try {
        	
            CurrencyQuoteDAO quoteDAO = new CurrencyQuoteDAO();
            System.out.println("getting currency");
            List<CurrencyQuote> currencyQuotes = quoteDAO.getQuotesByUserId(user.getId());

            InvestmentPlanDAO investmentDAO = new InvestmentPlanDAO();
            System.out.println("getting investment");
            List<InvestmentPlan> investmentQuotes = investmentDAO.getQuotesByUserId(user.getId());
            System.out.println(investmentQuotes);
            System.out.println("test profile");
            model.addAttribute("currencyQuotes", currencyQuotes);
            model.addAttribute("investmentQuotes", investmentQuotes);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("currencyQuotes", List.of());
            model.addAttribute("investmentQuotes", List.of());
        }

        model.addAttribute("user", user);
        return "profile";
    }
}
