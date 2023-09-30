import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI extends JFrame {
    private JTextField temperatureField;
    private JComboBox<String> originalUnitComboBox;
    private JLabel resultLabel;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel temperatureLabel = new JLabel("Enter temperature:");
        temperatureField = new JTextField();
        originalUnitComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel();

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });

        panel.add(temperatureLabel);
        panel.add(temperatureField);
        panel.add(new JLabel("Select original unit:"));
        panel.add(originalUnitComboBox);
        panel.add(new JLabel("Converted temperatures:"));
        panel.add(resultLabel);
        panel.add(new JLabel()); // Empty cell for spacing
        panel.add(convertButton);

        add(panel);

        setVisible(true);
    }

    private void convertTemperature() {
        try {
            double temperature = Double.parseDouble(temperatureField.getText());
            String originalUnit = originalUnitComboBox.getSelectedItem().toString();
            String result = "";

            if (originalUnit.equals("Celsius")) {
                result = temperature + " Celsius is equivalent to "
                        + celsiusToFahrenheit(temperature) + " Fahrenheit and "
                        + celsiusToKelvin(temperature) + " Kelvin";
            } else if (originalUnit.equals("Fahrenheit")) {
                result = temperature + " Fahrenheit is equivalent to "
                        + fahrenheitToCelsius(temperature) + " Celsius and "
                        + fahrenheitToKelvin(temperature) + " Kelvin";
            } else if (originalUnit.equals("Kelvin")) {
                result = temperature + " Kelvin is equivalent to "
                        + kelvinToCelsius(temperature) + " Celsius and "
                        + kelvinToFahrenheit(temperature) + " Fahrenheit";
            }

            resultLabel.setText(result);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a valid temperature.");
        }
    }

    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }

    private double fahrenheitToKelvin(double fahrenheit) {
        return celsiusToKelvin(fahrenheitToCelsius(fahrenheit));
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    private double kelvinToFahrenheit(double kelvin) {
        return celsiusToFahrenheit(kelvinToCelsius(kelvin));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureConverterGUI();
            }
        });
    }
}
