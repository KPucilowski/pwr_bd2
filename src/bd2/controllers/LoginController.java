package bd2.controllers;

import bd2.App;
import bd2.models.LoginModel;
import bd2.views.LoginView;

import javax.swing.*;
import java.sql.SQLException;

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
        view.getLoginButton().addActionListener(e -> login());
    }

    @Override
    public void dispose() {
        view.dispose();
    }

    private void login() {
        var login = view.getLoginField().getText();
        var pass = new String(view.getPasswordField().getPassword()); // not safe, don du zis

        try {
            model.login(login, pass);
            if (model.getType() != null) {
                App.reconnect(model, "pass");
                dispose();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
