package bd2.controllers;

import bd2.App;
import bd2.models.UserModel;
import bd2.views.LoginView;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class LoginController implements IController {
    private final LoginView view;
    private final UserModel model;

    public LoginController(LoginView view, UserModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void init() {
        view.getLoginButton().addActionListener(e -> logIn());
    }

    private void logIn() {
        var login = view.getLoginField().getText();
        var pass = new String(view.getPasswordField().getPassword()); // not safe, don du zis

        model.setType(checkLogin(login, pass));
        model.setId(findId(login, pass));

        App.reconnect(model, "pass");
        view.dispose();
    }

    private String checkLogin(String login, String pass) {
        try {
            CallableStatement stmt = App.cn.prepareCall("{? = call BD.CHECK_LOGIN(?, ?)}");
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setString(2, login);
            stmt.setString(3, pass);
            stmt.execute();

            return stmt.getString(1);
        } catch (SQLException throwable) {
            JOptionPane.showMessageDialog(null, throwable.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throwable.printStackTrace();
        }

        return null;
    }

    private int findId(String login, String pass) {
        try {
            CallableStatement stmt = App.cn.prepareCall("{? = call BD.GET_ID(?, ?)}");
            stmt.registerOutParameter(1, Types.NUMERIC);
            stmt.setString(2, login);
            stmt.setString(3, pass);
            stmt.execute();

            return stmt.getInt(1);
        } catch (SQLException throwable) {
            JOptionPane.showMessageDialog(null, throwable.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throwable.printStackTrace();
        }

        return -1;
    }
}
