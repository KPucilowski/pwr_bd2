package bd2.controllers;

import bd2.App;
import bd2.models.LoginModel;
import bd2.views.LoginView;

import javax.swing.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        try {
            var login = view.getLoginField().getText();
            var pass = charToSha256(view.getPasswordField().getPassword());

            model.login(login, pass);
            if (model.getType() != null) {
                App.reconnect(model, "pass");
                dispose();
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static String charToSha256(char[] hash) throws NoSuchAlgorithmException {
        byte[] res = new byte[hash.length];
        for (int i = 0; i < hash.length; i++) {
            res[i] = (byte) hash[i];
        }

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(res);
        byte[] digest = md.digest();

        return String.format("%064x", new BigInteger(1, digest));
    }
}
