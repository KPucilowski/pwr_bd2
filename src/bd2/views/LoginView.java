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
    private String userType;
    private JFrame frame;

    public LoginView() {
        frame = new JFrame("Login");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(350, 250));
        frame.setVisible(true);
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
