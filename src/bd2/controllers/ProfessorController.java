package bd2.controllers;

import bd2.App;
import bd2.models.LoginModel;
import bd2.models.ProfessorModel;
import bd2.views.ProfessorView;

import javax.swing.*;
import java.sql.SQLException;

public class ProfessorController implements IController {
    private final ProfessorView view;
    private final ProfessorModel model;

    public ProfessorController(ProfessorView view, LoginModel model) {
        this.view = view;
        this.model = new ProfessorModel(model);

        init();
    }

    public ProfessorController(ProfessorView view, ProfessorModel model) {
        this.view = view;
        this.model = model;

        init();
    }

    @Override
    public void init() {
        showPersonalData();
        view.getIdField().setText(String.valueOf(model.getId()));
        view.getPersonalDataButton().addActionListener(e -> showPersonalData());
        view.getLogOutButton().addActionListener(e -> dispose());
    }

    @Override
    public void dispose() {
        view.dispose();
        App.reconnect();
        new LoginController();
    }

    private void showPersonalData() {
        view.getTableModel().setRowCount(0);
        view.getTableModel().setColumnIdentifiers(new String[]{"First name", "Last name", "Email", "Faculty", "Degree"});
        try {
            var rs = model.getPersonalData();
            while (rs.next()) {
                var first_name = rs.getString("FIRST_NAME");
                var last_name = rs.getString("LAST_NAME");
                var email = rs.getString("EMAIL");
                var faculty = rs.getString("FACULTY");
                var degree = rs.getString("DEGREE");
                view.getTableModel().addRow(new String[]{first_name, last_name, email, faculty, degree });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


}
