package com.enomy.model;

import java.sql.Timestamp;

public class InvestmentPlan {
    private int id;
    private int userId;
    private String planType;
    private double lumpSum;
    private double monthlyContribution;
    private int years;
    private double minReturnRate;
    private double maxReturnRate;
    private double fee;
    private double tax;
    private double profit;
    private double finalAmount;
    private Timestamp quoteDate;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public double getLumpSum() {
        return lumpSum;
    }

    public void setLumpSum(double lumpSum) {
        this.lumpSum = lumpSum;
    }

    public double getMonthlyContribution() {
        return monthlyContribution;
    }

    public void setMonthlyContribution(double monthlyContribution) {
        this.monthlyContribution = monthlyContribution;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public double getMinReturnRate() {
        return minReturnRate;
    }

    public void setMinReturnRate(double minReturnRate) {
        this.minReturnRate = minReturnRate;
    }

    public double getMaxReturnRate() {
        return maxReturnRate;
    }

    public void setMaxReturnRate(double maxReturnRate) {
        this.maxReturnRate = maxReturnRate;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public Timestamp getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(Timestamp quoteDate) {
        this.quoteDate = quoteDate;
    }
}
