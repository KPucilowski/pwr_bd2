package bd2.controllers;

import bd2.App;
import bd2.models.LoginModel;
import bd2.models.DeansWorkerModel;
import bd2.views.WorkerView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class DeansWorkerController implements IController {
    private final WorkerView view;
    private final DeansWorkerModel model;

    public DeansWorkerController(WorkerView view, LoginModel model) {
        this.view = view;
        this.model = new DeansWorkerModel(model);

        init();
    }

    public DeansWorkerController(WorkerView view, DeansWorkerModel model) {
        this.view = view;
        this.model = model;

        init();
    }

    @Override
    public void init() {

        view.getIdField().setText(String.valueOf(model.getId()));
        view.getLogOutButton().addActionListener(e -> dispose());
        view.getAddStudentsToListButton().addActionListener(e -> addStudentToList());
        view.getAddStudentToGroupButton().addActionListener(e -> addStudentToGroup());
        view.getDeleteStudentsFromListButton().addActionListener(e -> deleteStudentsFromList());
        view.getDeleteStudentFromGroupButton().addActionListener(e -> deleteStudentsFromGroup());
        view.getDeleteGroupButton().addActionListener(e -> deleteGroup());
        view.getEditGroupButton().addActionListener(e -> editGroup());
        view.getOpenNewGroupButton().addActionListener(e -> openNewGroup());
    }

    private void editGroup() {
    }

    private void deleteGroup() {
        view.dataTable.setModel(view.tableModel);

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

        view.dataTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    JTable target = (JTable)me.getSource();
                    String string_group_id = (String) view.dataTable.getValueAt(target.getSelectedRow(), 0);
                    int cliked_group_id = Integer.parseInt(string_group_id);
                    System.out.println(cliked_group_id);
                    try {
                        model.removeGroup(cliked_group_id);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }


    private void deleteStudentsFromGroup() {
        view.dataTable.setModel(view.tableModel);
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

        view.dataTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    JTable target = (JTable)me.getSource();
                    String string_group_id = (String) view.dataTable.getValueAt(target.getSelectedRow(), 0);
                    int clicked_group_id = Integer.parseInt(string_group_id);
                    System.out.println(clicked_group_id);
                    showStudents();
                }
            }
        });
    }
    private void showStudents() {
        view.dataTable.repaint();
        view.getTableModel2().setRowCount(0);
        view.dataTable.setModel(view.tableModel2);
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


    private void deleteStudentsFromList() {/*
        view.dataTable.repaint();
        view.getTableModel2().setRowCount(0);
        view.dataTable.setModel(view.tableModel2);
        view.getTableModel2().setColumnIdentifiers(new String[]{"Student ID", "Group ID","Grade", "Student name", "Email"});
        try {
            var rs = model.getStudents(clicked_group_id);
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
        */
    }




    private void addStudentToGroup() {

    }

    private void addStudentToList() {
    }


    @Override
    public void dispose() {
        view.dispose();
        App.reconnect();
        new LoginController();
    }


    private void openNewGroup() {
    }
}