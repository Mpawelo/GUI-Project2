package GUI;

import Usage.Job;
import Usage.Work;
import Usage.Work.WorkType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewWorkFrame extends JFrame {
    private JPanel panel;
    private final JComboBox<WorkType> workTypeComboBox;
    private final JTextField descriptionTextField;
    private final JTextField workTimeTextField;
    private JButton addButton;

    private final JPanel dataPanel;
    private boolean isJobSelected;

    public NewWorkFrame(boolean isJobSelected, JPanel dataPanel) {
        this.isJobSelected = isJobSelected;
        this.dataPanel = dataPanel;

        setTitle("New Work");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        panel = new JPanel(new GridLayout(4, 2, 10, 10));

        workTypeComboBox = new JComboBox<>(WorkType.values());
        descriptionTextField = new JTextField();
        workTimeTextField = new JTextField();
        addButton = new JButton("Add Work");

        panel.add(new JLabel("Work Type:"));
        panel.add(workTypeComboBox);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionTextField);
        panel.add(new JLabel("Work Time:"));
        panel.add(workTimeTextField);
        panel.add(new JLabel());
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description = descriptionTextField.getText();
                String workTimeText = workTimeTextField.getText();

                if (description.isEmpty() || workTimeText.isEmpty()) {
                    JOptionPane.showMessageDialog(NewWorkFrame.this,
                            "Please enter all work details.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int workTime;
                try {
                    workTime = Integer.parseInt(workTimeText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(NewWorkFrame.this,
                            "Invalid work time. Please enter a numeric value.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                WorkType workType = (WorkType) workTypeComboBox.getSelectedItem();
                Work newWork = new Work(workType, description, workTime);

                if (isJobSelected) {
                    Job.addWork(newWork);
                    refreshDataPanel();
                }

                dispose();
            }
        });

        add(panel);
    }

    private void refreshDataPanel() {
        dataPanel.removeAll();
        dataPanel.add(new JLabel("Works"));
        dataPanel.add(new JList(Job.getWorkList().toArray()));

        dataPanel.revalidate();
        dataPanel.repaint();
    }
}


