package GUI;

import Department.StaffDepartment;
import Staff.StaffMember;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class EditStaffMemberFrame extends JFrame {
    private final StaffMember staffMember;
    private final JTextField nameTextField;
    private final JTextField surnameTextField;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;
    private final JComboBox<StaffDepartment> departmentComboBox;
    private JButton saveButton;

    public EditStaffMemberFrame(StaffMember staffMember) {
        this.staffMember = staffMember;
        setTitle("Edit Staff Member");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField(staffMember.getStaffMemberName());
        JLabel surnameLabel = new JLabel("Surname:");
        surnameTextField = new JTextField(staffMember.getStaffMemberSurname());
        JLabel dateOfBirthLabel = new JLabel("Date of Birth:");
        dayComboBox = new JComboBox<>(getDaysArray());
        monthComboBox = new JComboBox<>(getMonthsArray());
        yearComboBox = new JComboBox<>(getYearsArray());
        JLabel departmentLabel = new JLabel("Department:");
        departmentComboBox = new JComboBox<>(StaffDepartment.getDepartments().toArray(new StaffDepartment[0]));
        saveButton = new JButton("Save");

        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(surnameLabel);
        panel.add(surnameTextField);
        panel.add(dateOfBirthLabel);
        panel.add(createDateOfBirthPanel());
        panel.add(departmentLabel);
        panel.add(departmentComboBox);
        panel.add(new JLabel());
        panel.add(saveButton);

        add(panel);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });
    }

    private JPanel createDateOfBirthPanel() {
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        String[] days = getDaysArray();
        String[] months = getMonthsArray();
        String[] years = getYearsArray();

        dayComboBox = new JComboBox<>(days);
        monthComboBox = new JComboBox<>(months);
        yearComboBox = new JComboBox<>(years);

        datePanel.add(dayComboBox);
        datePanel.add(new JLabel(" / "));
        datePanel.add(monthComboBox);
        datePanel.add(new JLabel(" / "));
        datePanel.add(yearComboBox);

        return datePanel;
    }

    private String[] getDaysArray() {
        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) {
            days[i - 1] = String.valueOf(i);
        }
        return days;
    }

    private String[] getMonthsArray() {
        String[] months = new String[12];
        for (int i = 1; i <= 12; i++) {
            months[i - 1] = String.valueOf(i);
        }
        return months;
    }

    private String[] getYearsArray() {
        int startingYear = 1900;
        int endingYear = 2023;
        int numberOfYears = endingYear - startingYear + 1;
        String[] years = new String[numberOfYears];

        for (int i = 0; i < numberOfYears; i++) {
            years[i] = String.valueOf(endingYear - i);
        }

        return years;
    }

    private void saveChanges() {
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String day = (String) dayComboBox.getSelectedItem();
        String month = (String) monthComboBox.getSelectedItem();
        String year = (String) yearComboBox.getSelectedItem();
        StaffDepartment department = (StaffDepartment) departmentComboBox.getSelectedItem();

        if (name.isEmpty() || surname.isEmpty() || day == null || month == null || year == null || department == null) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int dayValue = Integer.parseInt(day);
        int monthValue = Integer.parseInt(month);
        int yearValue = Integer.parseInt(year);

        LocalDate dateOfBirth = LocalDate.of(yearValue, monthValue, dayValue);

        staffMember.setStaffMemberName(name);
        staffMember.setStaffMemberSurname(surname);
        staffMember.setDateOfBirth(dateOfBirth.toString());
        staffMember.setStaffDepartment(department);

        JOptionPane.showMessageDialog(this, "Changes saved.", "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose();

    }
}
