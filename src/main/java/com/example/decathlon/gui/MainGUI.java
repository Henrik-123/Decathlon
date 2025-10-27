package com.example.decathlon.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import com.example.decathlon.deca.*;
import com.example.decathlon.heptathlon.*;
import com.example.decathlon.common.InvalidResultException;
import com.example.decathlon.excel.ExcelPrinter;

public class MainGUI {

    private enum Mode { DEC, HEP }

    private JFrame frame;
    private JTextField nameField;
    private JTextField resultField;
    private JComboBox<String> disciplineBox;
    private JTextArea outputArea;
    private JRadioButton decRadio;
    private JRadioButton hepRadio;
    private JTable standingsTable;
    private DefaultTableModel standingsModel;
    private Mode mode = Mode.DEC;

    private final Map<Mode, LinkedHashMap<String, Map<String, Integer>>> scoresStore = new EnumMap<>(Mode.class);

    public MainGUI() {
        scoresStore.put(Mode.DEC, new LinkedHashMap<>());
        scoresStore.put(Mode.HEP, new LinkedHashMap<>());
    }

    private Map<String, Map<String, Integer>> scoresFor(Mode m) {
        return scoresStore.get(m);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().createAndShowGUI());
    }

    private void createAndShowGUI() {
        frame = new JFrame("Track and Field Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        JPanel root = new JPanel(new BorderLayout(10,10));
        JPanel top = new JPanel(new GridLayout(0,1,6,6));

        JPanel modePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        decRadio = new JRadioButton("Decathlon", true);
        hepRadio = new JRadioButton("Heptathlon");
        ButtonGroup g = new ButtonGroup();
        g.add(decRadio); g.add(hepRadio);
        modePanel.add(new JLabel("Mode:"));
        modePanel.add(decRadio);
        modePanel.add(hepRadio);
        top.add(modePanel);

        JPanel form = new JPanel(new GridLayout(0,2,6,6));
        nameField = new JTextField(20);
        form.add(new JLabel("Enter Competitor's Name:"));
        form.add(nameField);

        disciplineBox = new JComboBox<>(getEventsForMode(Mode.DEC).toArray(new String[0]));
        form.add(new JLabel("Select Discipline:"));
        form.add(disciplineBox);

        resultField = new JTextField(10);
        form.add(new JLabel("Enter Result:"));
        form.add(resultField);

        JButton calcBtn = new JButton("Calculate Score");
        JButton exportBtn = new JButton("Export to Excel");

        top.add(form);
        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnRow.add(calcBtn);
        btnRow.add(exportBtn);
        top.add(btnRow);

        outputArea = new JTextArea(6, 40);
        outputArea.setEditable(false);
        JScrollPane outScroll = new JScrollPane(outputArea);

        JPanel center = new JPanel(new BorderLayout(6,6));
        center.add(outScroll, BorderLayout.CENTER);

        standingsModel = buildStandingsModelFor(mode);
        standingsTable = new JTable(standingsModel);
        JScrollPane standingsScroll = new JScrollPane(standingsTable);
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(new JLabel("Standings"), BorderLayout.NORTH);
        bottom.add(standingsScroll, BorderLayout.CENTER);

        root.add(top, BorderLayout.NORTH);
        root.add(center, BorderLayout.CENTER);
        root.add(bottom, BorderLayout.SOUTH);

        decRadio.addActionListener(e -> switchMode(Mode.DEC));
        hepRadio.addActionListener(e -> switchMode(Mode.HEP));

        calcBtn.addActionListener(e -> onCalculate());
        exportBtn.addActionListener(e -> onExport());

        frame.setContentPane(root);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void switchMode(Mode newMode) {
        if (mode == newMode) return;
        mode = newMode;
        outputArea.setText("");
        nameField.setText("");
        resultField.setText("");
        disciplineBox.removeAllItems();
        for (String d : getEventsForMode(mode)) disciplineBox.addItem(d);
        standingsModel = buildStandingsModelFor(mode);
        standingsTable.setModel(standingsModel);
        refreshStandings();
    }

    private List<String> getEventsForMode(Mode m) {
        if (m == Mode.DEC) {
            return List.of("100m","400m","1500m","110m Hurdles","Long Jump","High Jump","Pole Vault","Discus Throw","Javelin Throw","Shot Put");
        } else {
            return List.of("100m Hurdles","High Jump","Shot Put","200m","Long Jump","Javelin Throw","800m");
        }
    }

    private DefaultTableModel buildStandingsModelFor(Mode m) {
        List<String> cols = new ArrayList<>();
        cols.add("Rank");
        cols.add("Name");
        cols.addAll(getEventsForMode(m));
        cols.add("Total");
        return new DefaultTableModel(cols.toArray(new String[0]), 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
    }

    private void onCalculate() {
        String name = nameField.getText() == null ? "" : nameField.getText().trim();
        if (name.isEmpty() || !name.matches(".*[a-zA-ZåäöÅÄÖ].*") || name.matches("^[\\d\\s\\p{Punct}]+$")) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid competitor name.", "Invalid Name", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String discipline = (String) disciplineBox.getSelectedItem();
        String text = resultField.getText() == null ? "" : resultField.getText().trim();
        double value;
        try {
            value = Double.parseDouble(text.replace(',', '.'));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number for the result.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int score = calculateScore(mode, discipline, value);
            scoresFor(mode).computeIfAbsent(name, k -> new HashMap<>()).put(discipline, score);
            outputArea.append("Competitor: " + name + "\n");
            outputArea.append("Discipline: " + discipline + "\n");
            outputArea.append("Result: " + value + "\n");
            outputArea.append("Score: " + score + "\n\n");
            nameField.setText("");
            resultField.setText("");
            refreshStandings();
        } catch (InvalidResultException ire) {
            JOptionPane.showMessageDialog(frame, ire.getMessage(), "Invalid Result", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onExport() {
        try {
            ExcelPrinter printer = new ExcelPrinter(mode == Mode.DEC ? "Decathlon" : "Heptathlon");

            List<String> cols = new ArrayList<>();
            cols.add("Rank");
            cols.add("Name");
            cols.addAll(getEventsForMode(mode));
            cols.add("Total");

            int rows = standingsModel.getRowCount();
            int colsCount = standingsModel.getColumnCount();

            Object[][] data = new Object[rows + 1][colsCount];
            for (int c = 0; c < colsCount; c++) data[0][c] = standingsModel.getColumnName(c);
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < colsCount; c++) {
                    data[r + 1][c] = standingsModel.getValueAt(r, c);
                }
            }

            printer.add(data, mode == Mode.DEC ? "Decathlon" : "Heptathlon");
            printer.write();
            JOptionPane.showMessageDialog(frame, "Excel file saved successfully!", "Export Complete", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error while writing Excel file: " + e.getMessage(), "Export Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int calculateScore(Mode m, String discipline, double val) throws InvalidResultException {
        if (m == Mode.DEC) {
            switch (discipline) {
                case "100m": return new Deca100M().calculateResult(val);
                case "400m": return new Deca400M().calculateResult(val);
                case "1500m": return new Deca1500M().calculateResult(val);
                case "110m Hurdles": return new Deca110MHurdles().calculateResult(val);
                case "Long Jump": return new DecaLongJump().calculateResult(val);
                case "High Jump": return new DecaHighJump().calculateResult(val);
                case "Pole Vault": return new DecaPoleVault().calculateResult(val);
                case "Discus Throw": return new DecaDiscusThrow().calculateResult(val);
                case "Javelin Throw": return new DecaJavelinThrow().calculateResult(val);
                case "Shot Put": return new DecaShotPut().calculateResult(val);
                default: throw new IllegalArgumentException("Unknown discipline");
            }
        } else {
            switch (discipline) {
                case "100m Hurdles": return new Hep100MHurdles().calculateResult(val);
                case "High Jump": return new HeptHightJump().calculateResult(val);
                case "Shot Put": return new HeptShotPut().calculateResult(val);
                case "200m": return new Hep200M().calculateResult(val);
                case "Long Jump": return new HeptLongJump().calculateResult(val);
                case "Javelin Throw": return new HeptJavelinThrow().calculateResult(val);
                case "800m": return new Hep800M().calculateResult(val);
                default: throw new IllegalArgumentException("Unknown discipline");
            }
        }
    }

    private void refreshStandings() {
        List<String> events = getEventsForMode(mode);
        List<Row> rows = new ArrayList<>();
        for (Map.Entry<String, Map<String,Integer>> e : scoresFor(mode).entrySet()) {
            String name = e.getKey();
            Map<String,Integer> ev = e.getValue();
            int total = ev.entrySet().stream().filter(x -> events.contains(x.getKey())).mapToInt(Map.Entry::getValue).sum();
            Map<String,Integer> filtered = new HashMap<>();
            for (String d: events) filtered.put(d, ev.getOrDefault(d, null));
            rows.add(new Row(name, filtered, total));
        }
        rows.sort((a,b) -> Integer.compare(b.total, a.total));
        standingsModel.setRowCount(0);
        int rank = 0;
        int index = 0;
        Integer prevTotal = null;
        for (Row r : rows) {
            index++;
            if (prevTotal == null || r.total != prevTotal) {
                rank = index;
                prevTotal = r.total;
            }
            List<Object> row = new ArrayList<>();
            row.add(rank);
            row.add(r.name);
            for (String d : events) row.add(r.eventScores.get(d) == null ? "" : r.eventScores.get(d));
            row.add(r.total);
            standingsModel.addRow(row.toArray());
        }
    }

    private static class Row {
        final String name;
        final Map<String,Integer> eventScores;
        final int total;
        Row(String n, Map<String,Integer> e, int t) { name = n; eventScores = e; total = t; }
    }
}
