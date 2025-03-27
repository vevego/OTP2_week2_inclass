
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;


public class HelloController {
    ResourceBundle rb;
    Locale locale;

    @FXML
    Pane basePane;

    @FXML
    private Label distanceLabel, fuelLabel, totalConsumptionLabel, resultsLabel, languageLabel;

    @FXML
    private TextField distanceTextField, fuelTextField;

    @FXML
    private Button calculateButton ,englishButton, frenchButton, japaneseButton, farsiButton;

    public void setLanguage(String language) {
        switch (language) {
            case "en":
                locale = new Locale("en", "US");
                break;
            case "fr":
                locale = new Locale("fr", "FR");
                break;
            case "ja":
                locale = new Locale("ja", "JP");
                break;
            case "fa":
                locale = new Locale("fa", "IR");
                break;
            default:
                locale = new Locale("en", "US");
        }
        rb = ResourceBundle.getBundle("texts", locale);
        distanceLabel.setText(rb.getString("distance"));
        fuelLabel.setText(rb.getString("fuel"));
        resultsLabel.setText(rb.getString("result"));
        languageLabel.setText(rb.getString("language"));
        calculateButton.setText(rb.getString("calculate"));

    }

    public void calculateFuelConsumption() {
        try {
            rb = ResourceBundle.getBundle("texts", locale);
            double distance = Double.parseDouble(distanceTextField.getText());
            double fuel = Double.parseDouble(fuelTextField.getText());
            double fuelConsumption = (fuel / distance) * 100;
            String result = MessageFormat.format(rb.getString("total"), String.format("%.2f", fuelConsumption));
            totalConsumptionLabel.setText(result);
        } catch (NumberFormatException e) {
            totalConsumptionLabel.setText(rb.getString("invalidInput"));
        }
    }

    public void clear () {
        distanceTextField.clear();
        fuelTextField.clear();
        totalConsumptionLabel.setText("");
    }

    public void initialize() {
        setLanguage("en");
        totalConsumptionLabel.setWrapText(true);
    }

    public void englishButtonClicked() {
        clear();
        setLanguage("en");
        basePane.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
    }

    public void frenchButtonClicked() {
        clear();
        setLanguage("fr");
        basePane.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
    }

    public void japaneseButtonClicked() {
        clear();
        setLanguage("ja");
        basePane.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
    }

    public void farsiButtonClicked() {
        clear();
        setLanguage("fa");
        basePane.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

    }

    public void calculateButtonClicked() {
        calculateFuelConsumption();
    }


}
