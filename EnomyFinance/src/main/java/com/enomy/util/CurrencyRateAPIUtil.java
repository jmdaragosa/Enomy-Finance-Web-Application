package com.enomy.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import org.json.JSONObject;

public class CurrencyRateAPIUtil {
    private static final String API_KEY = "e131e83ccaf53e938a2ea05f";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static double getLiveRate(String from, String to) {
        if (from == null || from.isEmpty() || to == null || to.isEmpty()) {
            System.err.println("Currency code missing: from=" + from + ", to=" + to);
            return -1;
        }

        try {
            String urlStr = BASE_URL + API_KEY + "/latest/" + from.toUpperCase();
            System.out.println("Requesting exchange rate: " + urlStr); // Logging the API call

            // Replacing deprecated URL constructor
            URI uri = new URI(urlStr);
            URL url = uri.toURL();

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                System.err.println("HTTP error code: " + responseCode);
                return -1;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();

            JSONObject json = new JSONObject(responseBuilder.toString());
            String result = json.optString("result", "error");

            if (!"success".equalsIgnoreCase(result)) {
                System.err.println("API response error: " + json.toString());
                return -1;
            }

            JSONObject rates = json.optJSONObject("conversion_rates");
            if (rates == null || !rates.has(to.toUpperCase())) {
                System.err.println("Target currency not found in API response: " + to);
                return -1;
            }

            double rate = rates.getDouble(to.toUpperCase());
            System.out.println("Exchange rate (" + from + " to " + to + "): " + rate);
            return rate;

        } catch (URISyntaxException e) {
            System.err.println("Invalid URL syntax: " + e.getMessage());
            return -1;
        } catch (Exception e) {
            System.err.println("Exception occurred while fetching live rate:");
            e.printStackTrace();
            return -1;
        }
    }
}
