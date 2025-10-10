package com.example.decathlon.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.example.decathlon.deca.*;
import com.example.decathlon.heptathlon.*;

public class MainGUI {

    private JTextField nameField;
    private JTextField resultField;
    private JComboBox<String> eventTypeBox;
    private JComboBox<String> disciplineBox;
    private JTextArea outputArea;

    // Decathlon events
    private final Map<String, Supplier<?>> decaEvents = new HashMap<>() {{
        put("100m", Deca100M::new);
        put("400m", Deca400M::new);
        put("1500m", Deca1500M::new);
        put("110m Hurdles", Deca110MHurdles::new);
        put("Long Jump", DecaLongJump::new);
        put("High Jump", DecaHighJump::new);
        put("Pole Vault", DecaPoleVault::new);
        put("Discus Throw", DecaDiscusThrow::new);
        put("Javelin Throw", DecaJavelinThrow::new);
        put("Shot Put", DecaShotPut::new);
    }};

    // Heptathlon events
    private final Map<String, Supplier<?>> heptathlonEvents = new HashMap<>() {{
        put("100m Hurdles", Hep100MHurdles::new);
        put("200m", Hep200M::new);
        put("800m", Hep800M::new);
        put("High Jump", HeptHightJump::new);
        put("Long Jump", HeptLongJump::new);
        put("Javelin Throw", HeptJavelinThrow::new);
        put("Shot Put", HeptShotPut::new);
    }};

    public static void main(String[] args) {
        new MainGUI().createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Track and Field Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JPanel panel = new JPanel(new GridLayout(9, 1, 5, 5));

        // Competitor name
        nameField = new JTextField(20);
        panel.add(new JLabel("Enter Competitor's Name:"));
        panel.add(nameField);

        // Event type combo box
        eventTypeBox = new JComboBox<>(new String[]{"Decathlon", "Heptathlon"});
        panel.add(new JLabel("Select Event Type:"));
        panel.add(eventTypeBox);

        // Discipline combo box
        disciplineBox = new JComboBox<>();
        panel.add(new JLabel("Select Discipline:"));
        panel.add(disciplineBox);

        // Update disciplines when event type changes
        eventTypeBox.addActionListener(e -> updateDisciplines());

        // Result input
        resultField = new JTextField(10);
        panel.add(new JLabel("Enter Result:"));
        panel.add(resultField);

        // Calculate button
        JButton calculateButton = new JButton("Calculate Score");
        calculateButton.addActionListener(this::calculateScore);
        panel.add(calculateButton);

        // Output area
        outputArea = new JTextArea(6, 40);
        outputArea.setEditable(false);
        panel.add(new JScrollPane(outputArea));

        frame.add(panel);
        frame.setVisible(true);

        // Initialize disciplines
        updateDisciplines();
    }

    private void updateDisciplines() {
        disciplineBox.removeAllItems();
        Map<String, Supplier<?>> events = eventTypeBox.getSelectedItem().equals("Decathlon") ? decaEvents : heptathlonEvents;
        for (String key : events.keySet()) {
            disciplineBox.addItem(key);
        }
    }

    private void calculateScore(ActionEvent e) {
        String name = nameField.getText();
        String discipline = (String) disciplineBox.getSelectedItem();
        String resultText = resultField.getText();
        String eventType = (String) eventTypeBox.getSelectedItem();

        try {
            double result = Double.parseDouble(resultText);

            if (!isValidResult(eventType, discipline, result)) {
                JOptionPane.showMessageDialog(
                        null,
                        "The entered value is not reasonable for this event.",
                        "Invalid Result",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            Supplier<?> supplier = eventType.equals("Decathlon") ? decaEvents.get(discipline) : heptathlonEvents.get(discipline);
            Object event = supplier.get();

            int score = (int) event.getClass().getMethod("calculateResult", double.class).invoke(event, result);

            outputArea.append("Competitor: " + name + "\n");
            outputArea.append("Event Type: " + eventType + "\n");
            outputArea.append("Discipline: " + discipline + "\n");
            outputArea.append("Result: " + result + "\n");
            outputArea.append("Score: " + score + "\n\n");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric result.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean isValidResult(String eventType, String discipline, double result) {
        if (eventType.equals("Decathlon")) {
            switch (discipline) {
                case "100m": return result >= 5 && result <= 17.8;
                case "400m": return result >= 30 && result <= 80;
                case "1500m": return result >= 200 && result <= 600;
                case "110m Hurdles": return result >= 10 && result <= 30;
                case "Long Jump": return result >= 300 && result <= 900;
                case "High Jump": return result >= 100 && result <= 250;
                case "Pole Vault": return result >= 200 && result <= 600;
                case "Discus Throw": return result >= 10 && result <= 80;
                case "Javelin Throw": return result >= 10 && result <= 100;
                case "Shot Put": return result >= 5 && result <= 25;
                default: return true;
            }
        } else { // Heptathlon
            switch (discipline) {
                case "100m Hurdles": return result >= 10 && result <= 20;
                case "200m": return result >= 20 && result <= 35;
                case "800m": return result >= 120 && result <= 300;
                case "High Jump": return result >= 100 && result <= 250;
                case "Long Jump": return result >= 300 && result <= 900;
                case "Javelin Throw": return result >= 10 && result <= 70;
                case "Shot Put": return result >= 5 && result <= 25;
                default: return true;
            }
        }
    }
}
