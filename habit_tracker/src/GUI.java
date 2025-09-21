import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    private Tracker tracker = new Tracker();
    private DefaultListModel<String> habitListModel = new DefaultListModel<>();
    private JList<String> habitList = new JList<>(habitListModel);

    public GUI() {
        setTitle("Habit Tracker");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Habit list in the center
        add(new JScrollPane(habitList), BorderLayout.CENTER);

        // Buttons at the bottom
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Habit");
        JButton confirmButton = new JButton("Confirm Habit");
        JButton resetButton = new JButton("Reset Habit");
        JButton refreshButton = new JButton("Refresh");

        buttonPanel.add(addButton);
        buttonPanel.add(confirmButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(refreshButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(e -> addHabit());
        confirmButton.addActionListener(e -> confirmHabit());
        resetButton.addActionListener(e -> resetHabit());
        refreshButton.addActionListener(e -> refreshList());

        refreshList();
    }

    private void addHabit() {
        String name = JOptionPane.showInputDialog(this, "Enter habit name:");
        if (name != null && !name.trim().isEmpty()) {
            tracker.addHabit(name.trim());
            refreshList();
        }
    }

    private void confirmHabit() {
        String selected = habitList.getSelectedValue();
        if (selected != null) {
            String name = selected.split("-")[0].replace("Habit [name=", "").trim();
            tracker.checkHabit(name);
            refreshList();
        }
    }

    private void resetHabit() {
        String selected = habitList.getSelectedValue();
        if (selected != null) {
            String name = selected.split("-")[0].replace("Habit [name=", "").trim();
            for (Habit h : tracker.habits) {
                if (h.getName().equals(name)) {
                    h.reset();
                    break;
                }
            }
            refreshList();
        }
    }

    private void refreshList() {
        habitListModel.clear();
        for (Habit h : tracker.habits) {
            habitListModel.addElement(h.toString());
        }
    }
}
