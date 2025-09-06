package com.enomy.util;

import com.enomy.model.InvestmentPlan;

public class InvestmentCalculator {

	public static void calculate(InvestmentPlan plan) {
	    double lumpSum = plan.getLumpSum();
	    double monthly = plan.getMonthlyContribution();
	    int years = plan.getYears();
	    String type = plan.getPlanType();

	    double minRate = 0, maxRate = 0, feeRate = 0;
	    double taxThreshold1 = 0, taxThreshold2 = 0;
	    double taxRate1 = 0, taxRate2 = 0;

	    // Plan parameters from your requirements
	    switch (type) {
	        case "Basic Savings":
	            minRate = 0.012;
	            maxRate = 0.024;
	            feeRate = 0.0025;
	            break;
	        case "Savings Plan Plus":
	            minRate = 0.03;
	            maxRate = 0.055;
	            feeRate = 0.003;
	            taxThreshold1 = 12000;
	            taxRate1 = 0.10;
	            break;
	        case "Managed Stock Investments":
	            minRate = 0.04;
	            maxRate = 0.23;
	            feeRate = 0.013;
	            taxThreshold1 = 12000;
	            taxThreshold2 = 40000;
	            taxRate1 = 0.10;
	            taxRate2 = 0.20;
	            break;
	    }

	    // Total contributed amount over the full period
	    double totalContribution = lumpSum + (monthly * 12 * years);
	    System.out.println("total contribution: " + totalContribution);

	    if ((type.equals("Basic Savings") && monthly < 50) ||
	        (type.equals("Savings Plan Plus") && (monthly < 50 || lumpSum < 300)) ||
	        (type.equals("Managed Stock Investments") && (monthly < 150 || lumpSum < 1000))) {
	        plan.setProfit(0);
	        plan.setFinalAmount(0);
	        plan.setFee(0);
	        plan.setTax(0);
	        plan.setMinReturnRate(0);
	        plan.setMaxReturnRate(0);
	        return;
	    }

	    // Lump sum future values
	    double lumpSumMinFV = lumpSum * Math.pow(1 + minRate, years);
	    double lumpSumMaxFV = lumpSum * Math.pow(1 + maxRate, years);

	    // Monthly contributions future values (annual compounding on yearly sum)
	    double yearlyContribution = monthly * 12;
	    double monthlyMinFV = 0;
	    double monthlyMaxFV = 0;

	    if (minRate > 0) {
	        monthlyMinFV = yearlyContribution * (Math.pow(1 + minRate, years) - 1) / minRate;
	    } else {
	        monthlyMinFV = yearlyContribution * years;
	    }

	    if (maxRate > 0) {
	        monthlyMaxFV = yearlyContribution * (Math.pow(1 + maxRate, years) - 1) / maxRate;
	    } else {
	        monthlyMaxFV = yearlyContribution * years;
	    }

	    double futureValueMin = lumpSumMinFV + monthlyMinFV;
	    double futureValueMax = lumpSumMaxFV + monthlyMaxFV;

	    // Fees = monthly fee rate * 12 months * years * future value
	    double totalMonths = years * 12;
	    double feeMin = futureValueMin * feeRate * years;
	    double feeMax = futureValueMax * feeRate * years;

	    // Profit before fees and taxes
	    double profitMin = futureValueMin - totalContribution;
	    double profitMax = futureValueMax - totalContribution;

	    // Tax calculation
	    double taxMin = 0;
	    double taxMax = 0;
	    
	    double finalAmountMin = futureValueMin - feeMin - taxMin;
	    double finalAmountMax = futureValueMax - feeMax - taxMax;
	    
	 // Average final amount for storing in plan
	    double avgFinalAmount = (finalAmountMin + finalAmountMax) / 2;

	    if (type.equals("Savings Plan Plus") || type.equals("Managed Stock Investments")) {
	        double threshold1 = 12000;
	        double threshold2 = type.equals("Managed Stock Investments") ? 40000 : 0;
	        double rate1 = 0.10;
	        double rate2 = type.equals("Managed Stock Investments") ? 0.20 : 0;

	        if (profitMin > threshold1) {
	            if (threshold2 > 0 && profitMin > threshold2) {
	                taxMin = (threshold2 - threshold1) * rate1 + (profitMin - threshold2) * rate2;
	            } else {
	                taxMin = (profitMin - threshold1) * rate1;
	            }
	        }

	        if (profitMax > threshold1) {
	            if (threshold2 > 0 && profitMax > threshold2) {
	                taxMax = (threshold2 - threshold1) * rate1 + (profitMax - threshold2) * rate2;
	            } else {
	                taxMax = (profitMax - threshold1) * rate1;
	            }
	        }
	    }
	    
	    


	    // Set values (averaged)
	    plan.setMinReturnRate(minRate);
	    plan.setMaxReturnRate(maxRate);
	    plan.setFee((feeMin + feeMax) / 2);
	    plan.setTax((taxMin + taxMax) / 2);
	    plan.setProfit((profitMin + profitMax) / 2);
	    plan.setFinalAmount(avgFinalAmount);
	    
   }
}
