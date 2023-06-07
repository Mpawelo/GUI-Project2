package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private final JToggleButton userButton;
    private final JToggleButton foremanButton;
    private final JTextField loginTextField;
    private final JPasswordField passwordField;
    private final JButton loginButton;

    private static String userPassword = "user";
    private static String foremanPassword = "foreman";

    public LoginFrame() {
        setTitle("Login");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel welcomeLabel = new JLabel("Welcome to Company Management App!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setPreferredSize(new Dimension(600, 80));
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel rolePanel = new JPanel(new GridLayout(3, 1, 0, 50));
        userButton = createRoleButton("User");
        foremanButton = createRoleButton("Foreman");
        rolePanel.add(userButton);
        rolePanel.add(foremanButton);

        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);


        JLabel loginLabel = new JLabel("Login:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(loginLabel, gbc);

        loginTextField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        loginPanel.add(loginTextField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(passwordField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginTextField.getText();
                String password = new String(passwordField.getPassword());
                String selectedRole = getSelectedRole();

                if (validateLogin(selectedRole, login, password)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login Successful!");
                    showMainFrame(selectedRole);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Incorrect login or password!");
                }
            }
        });
        buttonPanel.add(loginButton);

        panel.add(rolePanel, BorderLayout.WEST);
        panel.add(loginPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private JToggleButton createRoleButton(String role) {
        JToggleButton button = new JToggleButton(role);
        button.setFocusPainted(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLoginButtonLabel(role);
                setRoleButtonSelection(button);
            }
        });
        return button;
    }

    private void updateLoginButtonLabel(String role) {
        loginButton.setText("Login as " + role);
    }

    private void setRoleButtonSelection(JToggleButton selectedButton) {
        userButton.setSelected(false);
        foremanButton.setSelected(false);
        selectedButton.setSelected(true);
    }

    String getSelectedRole() {
        if (userButton.isSelected()) {
            return "User";
        } else if (foremanButton.isSelected()) {
            return "Foreman";
        } else {
            return "";
        }
    }

    private boolean validateLogin(String role, String login, String password) {
        if (role.equals("User")) {
            return login.equals("user") && password.equals(userPassword);
        } else if (role.equals("Foreman")) {
            return login.equals("foreman") && password.equals(foremanPassword);
        } else {
            return false;
        }
    }

    private void showMainFrame(String role) {
        switch (role) {
            case "User" -> {
                UserFrame userFrame = new UserFrame();
                userFrame.setVisible(true);
            }
            case "Foreman" -> {
                ForemanFrame foremanFrame = new ForemanFrame();
                foremanFrame.setVisible(true);
            }
        }
    }

    public static void changePassword(String role, JPasswordField passwordField) {
        JFrame changePasswordFrame = new JFrame("Change Password");
        changePasswordFrame.setSize(300, 300);
        changePasswordFrame.setLocationRelativeTo(null);
        changePasswordFrame.setLayout(new FlowLayout());
        changePasswordFrame.setVisible(true);

        JLabel newPasswordLabel = new JLabel("New Password");
        JTextField newPasswordField = new JTextField(20);
        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        JTextField confirmPasswordField = new JTextField(20);
        JButton changePasswordButton = new JButton("Change Password");

        changePasswordFrame.add(newPasswordLabel);
        changePasswordFrame.add(newPasswordField);
        changePasswordFrame.add(confirmPasswordLabel);
        changePasswordFrame.add(confirmPasswordField);
        changePasswordFrame.add(changePasswordButton);

        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPassword = newPasswordField.getText();
                String confirmPassword = confirmPasswordField.getText();

                if (newPassword.equals(confirmPassword)) {
                    updatePassword(role, newPassword);
                    passwordField.setText(newPassword);
                    changePasswordFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(changePasswordFrame, "Passwords do not match");
                }
            }
        });
    }

    private static void updatePassword(String role, String newPassword) {
        if (role.equals("User")) {
            userPassword = newPassword;
        } else if (role.equals("Foreman")) {
            foremanPassword = newPassword;
        }
    }

}