package bd2.controllers;

import bd2.App;
import bd2.models.UserModel;
import bd2.views.LoginView;
import bd2.views.StudentView;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class LoginController {
    private final LoginView view;
    private final UserModel model;

    public LoginController(LoginView view, UserModel model) {
        this.view = view;
        this.model = model;
    }

    public void init() {
        view.getLoginButton().addActionListener(e -> executeLoginCheck());
    }

    private void executeLoginCheck() {
        CallableStatement stmt;
        var login = view.getLoginField().getText();
        var pass = new String(view.getPasswordField().getPassword()); // not safe, don du zis
        try {
            stmt = App.cn.prepareCall("{? = call BD.CHECK_LOGIN(?, ?)}");
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setString(2, login);
            stmt.setString(3, pass);
            stmt.execute();
            model.setType(stmt.getString(1));
        } catch (SQLException throwable) {
            JOptionPane.showMessageDialog(null, throwable.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throwable.printStackTrace();
        }

        App.reconnect(model.getType(), "pass");
        if(model.getType().equals("STUDENT"))
            new StudentView();
    }
}
