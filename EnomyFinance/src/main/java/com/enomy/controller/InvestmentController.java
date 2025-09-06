package com.enomy.controller;

import com.enomy.dao.InvestmentPlanDAO;
import com.enomy.model.InvestmentPlan;
import com.enomy.model.User;
import com.enomy.util.InvestmentCalculator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/investment")
public class InvestmentController {

    @GetMapping
    public String showForm() {
        return "investmentForm";
    }

    @PostMapping("/calculate")
    public String calculateInvestment(HttpServletRequest request, HttpSession session, Model model) {
        try {
            String planType = request.getParameter("planType");
            double lumpSum = Double.parseDouble(request.getParameter("lumpSum"));
            double monthly = Double.parseDouble(request.getParameter("monthlyContribution"));
            int years = Integer.parseInt(request.getParameter("durationYears"));

            InvestmentPlan plan = new InvestmentPlan();
            plan.setPlanType(planType);
            plan.setLumpSum(lumpSum);
            plan.setMonthlyContribution(monthly);
            plan.setYears(years);

            // Perform calculations
            InvestmentCalculator.calculate(plan);

            // Save to DB if user is logged in
            Object userObj = session.getAttribute("loggedInUser");
            if (userObj instanceof User) {
                User user = (User) userObj;
                plan.setUserId(user.getId());

                InvestmentPlanDAO dao = new InvestmentPlanDAO();
                dao.saveInvestmentQuote(plan);
            }

            model.addAttribute("result", plan); // JSTL uses ${result}
            return "investmentForm"; // Stay on same page to show result
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Something went wrong. Please try again.");
            return "investmentForm";
        }
    }
}
