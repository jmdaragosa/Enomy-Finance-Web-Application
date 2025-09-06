package com.enomy.controller;

import com.enomy.dao.CurrencyQuoteDAO;
import com.enomy.model.CurrencyConversion;
import com.enomy.model.CurrencyQuote;
import com.enomy.model.User;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.enomy.util.CurrencyRateAPIUtil;

import java.math.BigDecimal;

@Controller
public class CurrencyController {

    @GetMapping("/currency")
    public String showForm() {
        return "currencyConverter";
    }
    

    @PostMapping("/currency")
    public String convertCurrency(
            @RequestParam double amount,
            @RequestParam String fromCurrency,
            @RequestParam String toCurrency,
            HttpSession session,
            Model model) {

        if (amount < 300 || amount > 5000) {
            model.addAttribute("error", "Amount must be between 300 and 5000.");
            return "currencyConverter";
        }

        // Fetch live exchange rate
        double rate = CurrencyRateAPIUtil.getLiveRate(fromCurrency, toCurrency);
        if (rate <= 0) {
            model.addAttribute("error", "Unable to fetch live exchange rate. Please try again later.");
            return "currencyConverter";
        }

        // Initial conversion
        double converted = amount * rate;

        // Fee rate logic
        double feeRate;
        if (amount <= 500) feeRate = 0.035;
        else if (amount <= 1500) feeRate = 0.027;
        else if (amount <= 2500) feeRate = 0.020;
        else feeRate = 0.015;

        double feeInBaseCurrency = amount * feeRate;
        double feeInTargetCurrency = feeInBaseCurrency * rate;

        double finalAmount = converted - feeInTargetCurrency;

        // Prepare result object for UI
        CurrencyConversion result = new CurrencyConversion();
        result.setAmount(amount);
        result.setFromCurrency(fromCurrency);
        result.setToCurrency(toCurrency);
        result.setRate(rate);
        result.setFee(feeInTargetCurrency);
        result.setFinalAmount(finalAmount);

        model.addAttribute("result", result);
        model.addAttribute("feeRate", feeRate);


        // Save to database if logged in
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            CurrencyQuote quote = new CurrencyQuote();
            quote.setUserId(user.getId());
            quote.setOriginalAmount(BigDecimal.valueOf(amount));
            quote.setFromCurrency(fromCurrency);
            quote.setToCurrency(toCurrency);
            quote.setConvertedAmount(BigDecimal.valueOf(finalAmount));
            quote.setExchangeRate(BigDecimal.valueOf(rate));
            quote.setFee(BigDecimal.valueOf(feeInTargetCurrency));

            CurrencyQuoteDAO.saveQuote(quote);
        }

        return "currencyConverter";
    }
}
