package bd2.controllers;

import bd2.models.LoginModel;
import bd2.models.StudentModel;
import bd2.views.StudentView;

import javax.swing.*;
import java.sql.SQLException;

public class StudentController implements IController {
    private final StudentView view;
    private final StudentModel model;

    public StudentController(StudentView view, LoginModel model) {
        this.view = view;
        this.model = new StudentModel(model);

        init();
    }

    public StudentController(StudentView view, StudentModel model) {
        this.view = view;
        this.model = model;

        init();
    }

    @Override
    public void init() {
        showPersonalData();
        view.getIdField().setText(String.valueOf(model.getId()));
        view.getPersonalDataButton().addActionListener(e -> showPersonalData());
        view.getLogOutButton().addActionListener(e -> logOut());
        view.getGradesButton().addActionListener(e -> showGrades());
    }

    private void showGrades() {
        if (model.getGrades() == null)
            model.fetchGrades();

        view.getTableModel().setRowCount(0);
        view.getTableModel().setColumnIdentifiers(new String[]{"Group ID", "Record date", "Grade", "Grade date"});

        try {
            var rs = model.getGrades();
            while (rs.next()) {
                String group_id = Integer.toString(rs.getInt("GROUP_ID"));
                String record_date = rs.getString("RECORD_DATE");
                String grade = rs.getString("GRADE");
                String grade_date = rs.getString("GRADE_DATE");
                view.getTableModel().addRow(new String[]{group_id, record_date, grade, grade_date});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void showPersonalData() {
        if (model.getPersonalData() == null)
            model.fetchPersonalData();

        view.getTableModel().setRowCount(0);
        view.getTableModel().setColumnIdentifiers(new String[]{"First name", "Last name", "Email", "Faculty"});

        var data = model.getPersonalData();
        var first_name = data.firstName;
        var last_name = data.lastName;
        var email = data.email;
        var faculty = data.facultyName;
        view.getTableModel().addRow(new String[]{first_name, last_name, email, faculty});
    }

    private void logOut() {
        view.dispose();
        new LoginController();
    }
}
