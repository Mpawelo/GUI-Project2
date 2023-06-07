package GUI;

import Department.StaffDepartment;
import Exceptions.NotUniqueNameException;
import Staff.StaffMember;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserFrame extends JFrame {

    private final JToggleButton staffDepartmentButton;
    private final JToggleButton staffMemberButton;
    private final JButton logoutButton;
    private final JButton newButton;
    private final JButton editButton;
    private final JButton deleteButton;
    private final JButton changeColorsButton;
    private final JToggleButton changePasswordButton;
    private final JPanel panel;
    private final JPanel staffPanel;
    private final JPanel actionPanel;
    private final JPanel dataPanel;

    private boolean isStaffDepartmentSelected;
    private boolean isStaffMemberSelected;
    private boolean isDarkModeEnabled;


    public UserFrame() {
        setTitle("User Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        panel = new JPanel(new BorderLayout());


        staffPanel = new JPanel(new GridLayout(2, 1));
        staffDepartmentButton = createToggleButton("Staff Department");
        staffMemberButton = createToggleButton("Staff Member");
        changePasswordButton = createToggleButton("Change Password");
        logoutButton = createButton("Logout");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(staffDepartmentButton);
        buttonGroup.add(staffMemberButton);
        buttonGroup.add(changePasswordButton);

        JPanel staffButtonsPanel = new JPanel(new GridLayout(4, 1, 0, 10));
        staffButtonsPanel.add(staffDepartmentButton);
        staffButtonsPanel.add(staffMemberButton);
        staffButtonsPanel.add(changePasswordButton);
        staffButtonsPanel.add(logoutButton);
        logoutButton.setBackground(Color.RED);

        staffPanel.add(staffButtonsPanel);
        panel.add(staffPanel, BorderLayout.WEST);

        actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        newButton = createButton("New");
        editButton = createButton("Edit");
        deleteButton = createButton("Delete");
        changeColorsButton = createButton("Change Colors");

        changeColorsButton.setFocusPainted(false);
        changeColorsButton.setBackground(Color.WHITE);
        changeColorsButton.setForeground(Color.BLACK);


        actionPanel.add(newButton);
        actionPanel.add(editButton);
        actionPanel.add(deleteButton);
        actionPanel.add(changeColorsButton);
        panel.add(actionPanel, BorderLayout.NORTH);


        dataPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.add(dataPanel, BorderLayout.CENTER);


        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Options");
        JMenuItem saveItem = new JMenuItem("Save");


        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);


        add(panel);


        changeColorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleAppColors();
            }
        });


        staffDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStaffDepartmentSelected = true;
                isStaffMemberSelected = false;
                dataPanel.removeAll();
                dataPanel.revalidate();
                dataPanel.repaint();
                dataPanel.add(new JLabel("Staff Departments"));
                dataPanel.add(new JList(StaffDepartment.getDepartments().toArray()));

            }
        });

        staffMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStaffDepartmentSelected = false;
                isStaffMemberSelected = true;
                dataPanel.removeAll();
                dataPanel.revalidate();
                dataPanel.repaint();
                dataPanel.add(new JLabel("Staff Members"));
                dataPanel.add(new JList(StaffMember.getStaffMembers().toArray()));

            }
        });

        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFrame.changePassword("User", null);
            }
        });

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStaffDepartmentSelected) {
                    NewDepartmentFrame newDepartmentFrame = new NewDepartmentFrame(isStaffDepartmentSelected, dataPanel);
                    newDepartmentFrame.setVisible(true);
                } else if (isStaffMemberSelected) {
                    NewStaffMemberFrame newStaffMemberFrame = new NewStaffMemberFrame(isStaffMemberSelected, dataPanel);
                    newStaffMemberFrame.setVisible(true);
                }

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStaffDepartmentSelected) {
                    Object selectedDepartment = ((JList) dataPanel.getComponent(1)).getSelectedValue();

                    if (selectedDepartment != null) {
                        int confirm = JOptionPane.showConfirmDialog(UserFrame.this,
                                "Are you sure you want to delete this department?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            StaffDepartment departmentToRemove = (StaffDepartment) selectedDepartment;
                            StaffDepartment.getDepartments().remove(departmentToRemove);

                            refreshDataPanel("Staff Departments");
                        }
                    } else {
                        JOptionPane.showMessageDialog(UserFrame.this,
                                "Please select a department to delete.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else if (isStaffMemberSelected) {
                    Object selectedStaffMember = ((JList) dataPanel.getComponent(1)).getSelectedValue();

                    if (selectedStaffMember != null) {
                        int confirm = JOptionPane.showConfirmDialog(UserFrame.this,
                                "Are you sure you want to delete this staff member?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            StaffMember staffMemberToRemove = (StaffMember) selectedStaffMember;
                            StaffMember.getStaffMembers().remove(staffMemberToRemove);

                            refreshDataPanel("Staff Members");
                        }
                    } else {
                        JOptionPane.showMessageDialog(UserFrame.this,
                                "Please select a staff member to delete.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStaffDepartmentSelected) {
                    Object selectedDepartment = ((JList) dataPanel.getComponent(1)).getSelectedValue();

                    if (selectedDepartment != null) {
                        StaffDepartment departmentToEdit = (StaffDepartment) selectedDepartment;

                        String newDepartmentName = JOptionPane.showInputDialog(UserFrame.this,
                                "Enter a new name for the department:",
                                departmentToEdit.getDepartmentName());

                        if (newDepartmentName != null && !newDepartmentName.isEmpty()) {
                            try {
                                departmentToEdit.setDepartmentName(newDepartmentName);
                                refreshDataPanel("Staff Departments");
                            } catch (NotUniqueNameException exception) {
                                JOptionPane.showMessageDialog(UserFrame.this,
                                        "The department name is not unique. Please enter a different name.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(UserFrame.this,
                                "Please select a department to edit.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }else if (isStaffMemberSelected) {
                    Object selectedStaffMember = ((JList) dataPanel.getComponent(1)).getSelectedValue();

                    if (selectedStaffMember != null) {
                        StaffMember staffMemberToEdit = (StaffMember) selectedStaffMember;

                        // Open the EditStaffMemberFrame to edit the staff member
                        EditStaffMemberFrame editStaffMemberFrame = new EditStaffMemberFrame(staffMemberToEdit);
                        editStaffMemberFrame.setVisible(true);

                        // Refresh the data panel after editing
                        refreshDataPanel("Staff Members");
                    } else {
                        JOptionPane.showMessageDialog(UserFrame.this,
                                "Please select a staff member to edit.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
    }


    private JToggleButton createToggleButton(String text) {
        JToggleButton button = new JToggleButton(text);
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement button action if needed
            }
        });
        return button;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(Color.BLUE.brighter());
        button.setForeground(Color.WHITE);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        return button;
    }

    private void refreshDataPanel(String dataType) {
        dataPanel.removeAll();
        dataPanel.revalidate();
        dataPanel.repaint();
        dataPanel.add(new JLabel(dataType));

        if (dataType.equals("Staff Departments")) {
            dataPanel.add(new JList(StaffDepartment.getDepartments().toArray()));
        } else if (dataType.equals("Staff Members")) {
            dataPanel.add(new JList(StaffMember.getStaffMembers().toArray()));
        }
    }

    private void toggleAppColors() {
        if (isDarkModeEnabled) {
            setLightModeColors();
        } else {
            setDarkModeColors();
        }
        isDarkModeEnabled = !isDarkModeEnabled;
    }

    private void setDarkModeColors() {
        Color backgroundColor = Color.DARK_GRAY.brighter();
        Color foregroundColor = Color.WHITE;
        Color logoutButtonColor = Color.RED;
        Color actionButtonColor = Color.BLUE.brighter();

        panel.setBackground(backgroundColor);
        staffPanel.setBackground(backgroundColor);
        staffDepartmentButton.setBackground(backgroundColor);
        staffMemberButton.setBackground(backgroundColor);
        changePasswordButton.setBackground(backgroundColor);
        logoutButton.setBackground(logoutButtonColor);
        actionPanel.setBackground(backgroundColor);
        newButton.setBackground(actionButtonColor);
        editButton.setBackground(actionButtonColor);
        deleteButton.setBackground(actionButtonColor);
        dataPanel.setBackground(backgroundColor);
        changeColorsButton.setBackground(backgroundColor);

        staffDepartmentButton.setForeground(foregroundColor);
        staffMemberButton.setForeground(foregroundColor);
        changePasswordButton.setForeground(foregroundColor);
        logoutButton.setForeground(foregroundColor);
        newButton.setForeground(foregroundColor);
        editButton.setForeground(foregroundColor);
        deleteButton.setForeground(foregroundColor);
        changeColorsButton.setForeground(foregroundColor);

        panel.revalidate();
        panel.repaint();
    }

    private void setLightModeColors() {
        Color backgroundColor = Color.WHITE;
        Color foregroundColor = Color.BLACK;
        Color logoutButtonColor = Color.RED;
        Color actionButtonColor = Color.BLUE.brighter();

        panel.setBackground(backgroundColor);
        staffPanel.setBackground(backgroundColor);
        staffDepartmentButton.setBackground(backgroundColor);
        staffMemberButton.setBackground(backgroundColor);
        changePasswordButton.setBackground(backgroundColor);
        logoutButton.setBackground(logoutButtonColor);
        actionPanel.setBackground(backgroundColor);
        newButton.setBackground(actionButtonColor);
        editButton.setBackground(actionButtonColor);
        deleteButton.setBackground(actionButtonColor);
        dataPanel.setBackground(backgroundColor);
        changeColorsButton.setBackground(backgroundColor);

        staffDepartmentButton.setForeground(foregroundColor);
        staffMemberButton.setForeground(foregroundColor);
        changePasswordButton.setForeground(foregroundColor);
        changeColorsButton.setForeground(foregroundColor);

        panel.revalidate();
        panel.repaint();
    }


}