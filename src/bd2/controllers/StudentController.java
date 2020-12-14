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
        view.getLogOutButton().addActionListener(e -> dispose());
        view.getGradesButton().addActionListener(e -> showGrades());
    }

    @Override
    public void dispose() {
        view.dispose();
        new LoginController();
    }

    private void showGrades() {
        if (model.getGrades() == null)
            model.fetchGrades();

        view.getTableModel().setRowCount(0);
        view.getTableModel().setColumnIdentifiers(new String[]{"Group ID", "Record date", "Grade", "Grade date"});

        try {
            var rs = model.getGrades();
            while (rs.next()) {
                var group_id = Integer.toString(rs.getInt("GROUP_ID"));
                var record_date = rs.getString("RECORD_DATE");
                var grade = rs.getString("GRADE");
                var grade_date = rs.getString("GRADE_DATE");
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
        view.getTableModel().setColumnIdentifiers(new String[]{"First name", "Last name", "Email", "Faculty", "PESEL", "Year", "Semester", "Specialization", "Average grade"});
        try {
            var rs = model.getPersonalData();
            while (rs.next()) {
                var first_name = rs.getString("FIRST_NAME");
                var last_name = rs.getString("LAST_NAME");
                var email = rs.getString("EMAIL");
                var faculty = rs.getString("FACULTY");
                var year = rs.getString("YEAR");
                var pesel = rs.getString("PESEL");
                var semester = rs.getString("SEMESTER");
                var specialization = rs.getString("SPECIALIZATION");
                var avg_grade = String.valueOf(rs.getDouble("AVG_GRADE"));
                view.getTableModel().addRow(new String[]{first_name, last_name, email, faculty, pesel, year, semester, specialization, avg_grade});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
