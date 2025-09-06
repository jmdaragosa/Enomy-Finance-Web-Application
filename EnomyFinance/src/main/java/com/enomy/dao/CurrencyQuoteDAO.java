package com.enomy.dao;

import com.enomy.model.CurrencyQuote;
import com.enomy.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyQuoteDAO {

    public static void saveQuote(CurrencyQuote quote) {
        String sql = "INSERT INTO conversion_quote (user_id, original_amount, from_currency, to_currency, converted_amount, exchange_rate, fee) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quote.getUserId());
            stmt.setBigDecimal(2, quote.getOriginalAmount());
            stmt.setString(3, quote.getFromCurrency());
            stmt.setString(4, quote.getToCurrency());
            stmt.setBigDecimal(5, quote.getConvertedAmount());
            stmt.setBigDecimal(6, quote.getExchangeRate());
            stmt.setBigDecimal(7, quote.getFee());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CurrencyQuote> getQuotesByUserId(int userId) {
        List<CurrencyQuote> quotes = new ArrayList<>();
        String sql = "SELECT * FROM conversion_quote WHERE user_id = ? ORDER BY id DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CurrencyQuote quote = new CurrencyQuote();
                quote.setUserId(rs.getInt("user_id"));
                quote.setOriginalAmount(rs.getBigDecimal("original_amount"));
                quote.setFromCurrency(rs.getString("from_currency"));
                quote.setToCurrency(rs.getString("to_currency"));
                quote.setConvertedAmount(rs.getBigDecimal("converted_amount"));
                quote.setExchangeRate(rs.getBigDecimal("exchange_rate"));
                quote.setFee(rs.getBigDecimal("fee"));

                quotes.add(quote);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quotes;
    }
}
