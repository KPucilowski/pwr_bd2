package bd2;

import bd2.controllers.LoginController;
import bd2.controllers.StudentController;
import bd2.models.LoginModel;
import bd2.views.StudentView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class App {
    public static Connection cn;
    
    public static void reconnect() {
        try {
            cn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@146.59.17.101:1521:XE", "login_only", "pass");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void reconnect(LoginModel model, String pass) {
        try {
            cn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@146.59.17.101:1521:XE", model.getType(), pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (model.getType().equals("STUDENT")) {
            new StudentController(new StudentView(), model);
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
        reconnect();
        new LoginController();
    }
}
