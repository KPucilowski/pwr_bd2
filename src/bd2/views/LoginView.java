package bd2.views;

import javax.swing.*;
import java.awt.*;

public class LoginView {
    private JPanel contentPane;
    private JPanel loginPane;
    private JPanel passwordPane;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JPanel buttonPane;
    private JButton loginButton;
    private JPanel dataPane;
    private JLabel passwordLabel;
    private JLabel loginLabel;
    private JLabel systemLabel;
    private JLabel descLabel;
    private JPanel systemNamePane;
    private String userType;
    private final JFrame frame;

    public LoginView() {
        frame = new JFrame("Login");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        systemLabel.setFont(new Font("Calibri", Font.BOLD, 24));
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JTextField getLoginField() {
        return loginField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void dispose() {
        frame.dispose();
    }
}
