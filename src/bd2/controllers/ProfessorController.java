package bd2.controllers;

import bd2.App;
import bd2.models.LoginModel;
import bd2.models.ProfessorModel;
import bd2.views.ProfessorView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
        view.getTimetableButton().addActionListener(e -> showTimetable());
        view.getGroupsButton().addActionListener(e -> showStudents());
        view.getSaveButton().addActionListener(e -> updateGrades());
        view.getLogOutButton().addActionListener(e -> dispose());
    }


    @Override
    public void dispose() {
        view.dispose();
        App.reconnect();
        new LoginController();
    }

    private void showPersonalData() {
        view.dataTable.setModel(view.tableModel);
        view.saveButton.setVisible(false);
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
                view.getTableModel().addRow(new String[]{first_name, last_name, email, faculty, degree});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void showTimetable() {
        view.dataTable.setModel(view.tableModel);
        view.saveButton.setVisible(false);
        view.getTableModel().setRowCount(0);
        view.getTableModel().setColumnIdentifiers(new String[]{"Subject ID", "Subject name", "Day", "Time", "Parity", "Form", "Students limit"});
        try {
            var rs = model.getGroups();
            while (rs.next()) {
                var subject_id = rs.getString("SUBJECT_ID");
                var subject_name = rs.getString("SUBJECT_NAME");
                var day = rs.getString("DAY");
                var time = rs.getString("TIME");
                var parity = rs.getString("PARITY");
                var form = rs.getString("FORM");
                var student_limit = rs.getString("STUDENT_LIMIT");
                view.getTableModel().addRow(new String[]{subject_id, subject_name, day, time, parity, form, student_limit});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void showGroups() {
        view.dataTable.setModel(view.tableModel);
        view.saveButton.setVisible(false);
        view.getTableModel().setRowCount(0);
        view.getTableModel().setColumnIdentifiers(new String[]{"Group ID", "Subject ID", "Subject name", "Day", "Time", "Parity", "Form"});
        try {
            var rs = model.getGroups();
            while (rs.next()) {
                var group_id = rs.getString("GROUP_ID");
                var subject_id = rs.getString("SUBJECT_ID");
                var subject_name = rs.getString("SUBJECT_NAME");
                var day = rs.getString("DAY");
                var time = rs.getString("TIME");
                var parity = rs.getString("PARITY");
                var form = rs.getString("FORM");
                view.getTableModel().addRow(new String[]{group_id, subject_id, subject_name, day, time, parity, form});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void showStudents() {
        view.dataTable.setModel(view.tableModel2);
        view.saveButton.setVisible(true);
        view.getTableModel2().setRowCount(0);
        view.getTableModel2().setColumnIdentifiers(new String[]{"Student ID", "Group ID","Grade", "Student name", "Email"});
        try {
            var rs = model.getStudents();
            while (rs.next()) {
                var student_id = rs.getString("STUDENT_ID");
                var group_id = rs.getString("GROUP_ID");
                var student = rs.getString("STUDENT");
                var email = rs.getString("EMAIL");
                var grade = rs.getString("GRADE");
                view.getTableModel2().addRow(new String[]{student_id, group_id, grade, student, email});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void updateGrades() {
        view.dataTable.setModel(view.tableModel2);
        try {
            var rs = model.getStudents();
            while (rs.next()) {
                //????
                //model.addGrade(student_id,group_id, grade);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }
}
