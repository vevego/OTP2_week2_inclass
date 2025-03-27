
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
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

    public void setLanguage(Locale locale) {
        totalConsumptionLabel.setText("");

        try
        {
            rb = ResourceBundle.getBundle("texts", locale);
            distanceLabel.setText(rb.getString("distance"));
            fuelLabel.setText(rb.getString("fuel"));
            resultsLabel.setText(rb.getString("result"));
            languageLabel.setText(rb.getString("language"));
            calculateButton.setText(rb.getString("calculate"));
        } catch (MissingResourceException e) {
            e.printStackTrace();
            totalConsumptionLabel.setText("Error loading resources");
        }

    }

    public void calculateFuelConsumption() {
        totalConsumptionLabel.setText("");
        if (rb == null) {
            totalConsumptionLabel.setText("Error loading resources");
            return;
        }

        try {
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
        setLanguage(new Locale("en", "US"));
        totalConsumptionLabel.setWrapText(true);
    }

    public void englishButtonClicked() {
        clear();
        setLanguage(new Locale("en", "US"));
        basePane.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
    }

    public void frenchButtonClicked() {
        clear();
        setLanguage(new Locale("fr", "FR"));
        basePane.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
    }

    public void japaneseButtonClicked() {
        clear();
        setLanguage(new Locale("ja", "JP"));
        basePane.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
    }

    public void farsiButtonClicked() {
        clear();
        setLanguage(new Locale("fa", "IR"));
        basePane.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

    }

    public void calculateButtonClicked() {
        calculateFuelConsumption();
    }


}
