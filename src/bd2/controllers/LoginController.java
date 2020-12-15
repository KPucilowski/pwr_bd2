package bd2.controllers;

import bd2.App;
import bd2.models.LoginModel;
import bd2.views.LoginView;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class LoginController implements IController {
    private final LoginView view;
    private final LoginModel model;

    public LoginController(LoginView view, LoginModel model) {
        this.view = view;
        this.model = model;

        init();
    }

    public LoginController() {
        this.view = new LoginView();
        this.model = new LoginModel(null);

        init();
    }

    @Override
    public void init() {
        view.getLoginButton().addActionListener(e -> logIn());
    }

    @Override
    public void dispose() {
        view.dispose();
    }

    private void logIn() {
        var login = view.getLoginField().getText();
        var pass = new String(view.getPasswordField().getPassword()); // not safe, don du zis

        var type = checkLogin(login, pass);
        if (type != null) {
            model.setType(type);
            model.setId(findId(login, pass));

            App.reconnect(model, "pass");
            dispose();
        }
    }

    private String checkLogin(String login, String pass) {
        try {
            CallableStatement stmt = App.cn.prepareCall("{? = call BD.CHECK_LOGIN(?, ?)}");
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setString(2, login);
            stmt.setString(3, pass);
            stmt.execute();
            var temp = stmt.getString(1);
            return stmt.getString(1);
        } catch (SQLException throwable) {
            JOptionPane.showMessageDialog(null, "Wrong login/password or user does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
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
