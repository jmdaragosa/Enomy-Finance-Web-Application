package com.enomy.dao;

import com.enomy.model.InvestmentPlan;
import com.enomy.util.DBUtil; // Make sure this import matches your package

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvestmentPlanDAO {
    private Connection conn;

    // ✅ New no-argument constructor (auto-connects using DBUtil)
    public InvestmentPlanDAO() throws SQLException {
        this.conn = DBUtil.getConnection();
    }

    // ✅ Existing constructor (manual connection injection still works)
    public InvestmentPlanDAO(Connection conn) {
        this.conn = conn;
    }

    public void saveInvestmentQuote(InvestmentPlan plan) throws SQLException {
        String sql = "INSERT INTO investment_quote " +
                "(user_id, plan_type, lump_sum, monthly_contribution, years, min_return_rate, max_return_rate, fee, tax, profit, final_amount, quote_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, plan.getUserId());
            stmt.setString(2, plan.getPlanType());
            stmt.setDouble(3, plan.getLumpSum());
            stmt.setDouble(4, plan.getMonthlyContribution());
            stmt.setInt(5, plan.getYears());
            stmt.setDouble(6, plan.getMinReturnRate());
            stmt.setDouble(7, plan.getMaxReturnRate());
            stmt.setDouble(8, plan.getFee());
            stmt.setDouble(9, plan.getTax());
            stmt.setDouble(10, plan.getProfit());
            stmt.setDouble(11, plan.getFinalAmount());
            stmt.executeUpdate();
        }
    }

    public List<InvestmentPlan> getQuotesByUserId(int userId) throws SQLException {
        List<InvestmentPlan> list = new ArrayList<>();
        String sql = "SELECT * FROM investment_quote WHERE user_id = ? ORDER BY quote_date DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                InvestmentPlan plan = new InvestmentPlan();
                plan.setId(rs.getInt("id"));
                plan.setUserId(rs.getInt("user_id"));
                plan.setPlanType(rs.getString("plan_type"));
                plan.setLumpSum(rs.getDouble("lump_sum"));
                plan.setMonthlyContribution(rs.getDouble("monthly_contribution"));
                plan.setYears(rs.getInt("years"));
                plan.setMinReturnRate(rs.getDouble("min_return_rate"));
                plan.setMaxReturnRate(rs.getDouble("max_return_rate"));
                plan.setFee(rs.getDouble("fee"));
                plan.setTax(rs.getDouble("tax"));
                plan.setProfit(rs.getDouble("profit"));
                plan.setFinalAmount(rs.getDouble("final_amount"));
                plan.setQuoteDate(rs.getTimestamp("quote_date"));
                list.add(plan);
            }
        }
        return list;
    }
}
