import model.Users;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LoginForm extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginForm() {
        setTitle("Login Form");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the background panel with the image
        BackgroundPanel backgroundPanel = new BackgroundPanel("picture/compensation-vs-salary.png");
        backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        // Set preferred size for username and password fields
        Dimension fieldSize = new Dimension(200, 30);
        usernameField.setPreferredSize(fieldSize);
        passwordField.setPreferredSize(fieldSize);
        // Create panel for components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        backgroundPanel.add(usernameLabel, gbc);

        gbc.gridy++;
        backgroundPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        backgroundPanel.add(usernameField, gbc);

        gbc.gridy++;
        backgroundPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(loginButton, gbc);

        List<Users> lstuse = new ArrayList<>();
        Users user1 = new Users("admin1", "admin1");
        Users user2 = new Users("admin2", "admin2");
        lstuse.add(user1);
        lstuse.add(user2);

        // Add action listener to login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                boolean loggedIn = false;
                for (Users user : lstuse) {
                    if (user.getUsername().equals(username) && user.getPassworld().equals(password)) {
                        loggedIn = true;
                        break;
                    }
                }

                if (loggedIn) {
                    JOptionPane.showMessageDialog(LoginForm.this, "Login successful!");
                    JFrame jsram = new MenuForm();
                    jsram.setVisible(true);
                    setVisible(false);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Invalid username or password. Please try again.");
                    // Clear password field
                    passwordField.setText("");
                }
            }
        });

        // Set the background panel as the content pane
        setContentPane(backgroundPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
}
