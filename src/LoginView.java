import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginView implements ActionListener {
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

    LoginView() {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginButton.addActionListener(this);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(350, 250));
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object evt = e.getSource();
        if (evt == loginButton) {
            CallableStatement stmt;
            var login = loginField.getText();
            var pass = new String(passwordField.getPassword()); // not safe, don du zis
            try {
                stmt = App.cn.prepareCall("{? = call BDT.CHECK_LOGIN(?, ?)}");
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(2, login);
                stmt.setString(3, pass);
                stmt.execute();
                JOptionPane.showMessageDialog(null, stmt.getString(1), "Error", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException throwable) {
                JOptionPane.showMessageDialog(null, throwable.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                throwable.printStackTrace();
            }
        }
    }
}
