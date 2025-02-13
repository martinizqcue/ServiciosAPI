package org.example.apihttp;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CurrencyConverterController {

    @FXML
    private TextField amountField;

    @FXML
    private ComboBox<String> fromCurrencyComboBox;

    @FXML
    private ComboBox<String> toCurrencyComboBox;

    @FXML
    private Label resultLabel;

    @FXML
    protected void onConvertButtonClick() {
        String fromCurrency = fromCurrencyComboBox.getValue();
        String toCurrency = toCurrencyComboBox.getValue();
        double amount = Double.parseDouble(amountField.getText());

        try {
            double convertedAmount = HttpService.convertCurrency(fromCurrency, toCurrency, amount);
            resultLabel.setText(String.format("Cantidad convertida: %.2f", convertedAmount));
        } catch (IOException e) {
            resultLabel.setText("Error connecting to API");
        }
    }

    // Método para inicializar las monedas disponibles en los ComboBoxes
    @FXML
    public void initialize() {
        fromCurrencyComboBox.getItems().addAll("USD", "EUR", "GBP", "JPY", "INR");
        toCurrencyComboBox.getItems().addAll("USD", "EUR", "GBP", "JPY", "INR");
    }
}
