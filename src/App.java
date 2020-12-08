import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static Connection cn;

    static {
        try {
            cn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:XE", "login_only", "login_only");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        /* Set OS default look and feel */
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Start app */
        new LoginView();
    }
}
