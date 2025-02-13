package org.example.apihttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import org.json.JSONObject;

public class HttpService {

    private static final String API_KEY = "c7286dd944547b976dfa63bd";  // Reemplaza con tu clave API de la API de divisas
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/%s/latest/%s";

    public static double convertCurrency(String fromCurrency, String toCurrency, double amount) throws IOException, IOException {
        String url = String.format(API_URL, API_KEY, fromCurrency);
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Parseamos la respuesta JSON
        JSONObject jsonResponse = new JSONObject(response.toString());
        double conversionRate = jsonResponse.getJSONObject("conversion_rates").getDouble(toCurrency);

        return amount * conversionRate;
    }
}
