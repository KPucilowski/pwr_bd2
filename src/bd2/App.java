package bd2;
import bd2.controllers.LoginController;
import bd2.models.UserModel;
import bd2.views.LoginView;
import oracle.jdbc.logging.annotations.Log;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class App {
    public static Connection cn;

    static {
        try {
            cn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@146.59.17.101:1521:XE", "login_only", "pass");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void reconnect(String user, String pass) {
        try {
            cn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@146.59.17.101:1521:XE", user, pass);
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
        LoginController controller = new LoginController(new LoginView(), new UserModel(null));
        controller.init();
    }
}
