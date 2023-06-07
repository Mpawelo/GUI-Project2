package GUI;

import Department.StaffDepartment;
import Exceptions.NotUniqueNameException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class NewDepartmentFrame extends JFrame {
    private final JTextField departmentNameField;
    private JButton okButton;
    private JPanel dataPanel;
    private boolean isStaffDepartmentSelected;

    public NewDepartmentFrame(boolean isStaffDepartmentView, JPanel dataPanel) {
        this.isStaffDepartmentSelected = isStaffDepartmentSelected;
        this.dataPanel = dataPanel;
        setTitle("New Department");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel nameLabel = new JLabel("Department Name:");
        departmentNameField = new JTextField(20);
        okButton = new JButton("OK");

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.add(nameLabel);
        inputPanel.add(departmentNameField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(okButton);

        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStaffDepartmentView) {
                    String departmentName = departmentNameField.getText();

                    try {
                        StaffDepartment.createDepartment(departmentName);
                        dataPanel.removeAll();
                        dataPanel.revalidate();
                        dataPanel.repaint();
                        dataPanel.add(new JLabel("Staff Departments"));
                        dataPanel.add(new JList(StaffDepartment.getDepartments().toArray()));
                    } catch (NotUniqueNameException exception) {
                        JOptionPane.showMessageDialog(NewDepartmentFrame.this,
                                "Name of the department is not unique",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    dispose();
                }
            }
        });
    }
}
