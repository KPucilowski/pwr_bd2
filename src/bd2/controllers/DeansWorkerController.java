package bd2.controllers;

import bd2.App;
import bd2.models.LoginModel;
import bd2.models.DeansWorkerModel;
import bd2.views.WorkerView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class DeansWorkerController implements IController {
    private final WorkerView view;
    private final DeansWorkerModel model;
    public int group_id;
    public int student_id;

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
    public void dispose() {
        view.dispose();
        App.reconnect();
        new LoginController();
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
        view.getAccept_button().addActionListener(e->accept());
        view.getFirst_name().setVisible(false);
        view.getLast_name().setVisible(false);
        view.getFaculty().setVisible(false);
        view.getPesel().setVisible(false);
        view.getPeselField().setVisible(false);
        view.getLastNameField().setVisible(false);
        view.getFacultyField().setVisible(false);
        view.getFirstNameField().setVisible(false);
        view.getAccept_button().setVisible(false);
    }

    private void accept() {
        String first_name;
        String last_name;
        String faculty_id;
        String pesel;
        first_name = view.getFirst_name().getText();
        last_name = view.getLastNameField().getText();
        faculty_id = view.getFacultyField().getText();
        pesel = view.getPesel().getText();
        try {
            model.addStudent(first_name,last_name,faculty_id,pesel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        view.getFirst_name().setVisible(false);
        view.getLast_name().setVisible(false);
        view.getFaculty().setVisible(false);
        view.getPesel().setVisible(false);
        view.getPeselField().setVisible(false);
        view.getLastNameField().setVisible(false);
        view.getFacultyField().setVisible(false);
        view.getFirstNameField().setVisible(false);
        view.getAccept_button().setVisible(false);
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
                    showStudents(clicked_group_id);
                }
            }
        });
    }
    private void showStudents(int clicked_group_id) {
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
        view.dataTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    JTable target = (JTable)me.getSource();
                    String string_student_id = (String) view.dataTable.getValueAt(target.getSelectedRow(), 0);
                    int clicked_student_id = Integer.parseInt(string_student_id);
                    System.out.println(clicked_student_id);
                    try {

                        model.removeStudentFromGroup(clicked_student_id,clicked_group_id);

                    } catch (SQLException throwables) {

                        throwables.printStackTrace();

                    }
                }
            }
        });
    }


    private void deleteStudentsFromList() {
        view.dataTable.repaint();
        view.getTableModel2().setRowCount(0);
        view.dataTable.setModel(view.tableModel2);
        view.getTableModel2().setColumnIdentifiers(new String[]{"Student ID", "Group ID","Grade", "Student name", "Email"});
        try {
            var rs = model.getAllStudents();
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
        view.dataTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    JTable target = (JTable)me.getSource();
                    String string_student_id = (String) view.dataTable.getValueAt(target.getSelectedRow(), 0);
                    int clicked_student_id = Integer.parseInt(string_student_id);
                    System.out.println(clicked_student_id);
                    try {

                        model.removeStudent(clicked_student_id);

                    } catch (SQLException throwables) {

                        throwables.printStackTrace();

                    }
                }
            }
        });
    }




    private void addStudentToGroup() {
        view.dataTable.repaint();
        view.getTableModel2().setRowCount(0);
        view.dataTable.setModel(view.tableModel2);
        view.getTableModel2().setColumnIdentifiers(new String[]{"Student ID", "Group ID","Grade", "Student name", "Email"});
        try {
            var rs = model.getAllStudents();
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
        view.dataTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    JTable target = (JTable)me.getSource();
                    String string_student_id = (String) view.dataTable.getValueAt(target.getSelectedRow(), 0);
                    int clicked_student_id = Integer.parseInt(string_student_id);
                    System.out.println(clicked_student_id);
                    student_id = clicked_student_id;
                    showGroups();
                }
            }
        });

    }

    private void showGroups(){
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
                    group_id = clicked_group_id;
                    try {
                        model.addStudentToGroup(student_id,clicked_group_id);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }
    private void addStudentToList() {
        view.getFirst_name().setVisible(true);
        view.getLast_name().setVisible(true);
        view.getFaculty().setVisible(true);
        view.getPesel().setVisible(true);
        view.getAccept_button().setVisible(true);
        view.getLastNameField().setVisible(true);
        view.getFirstNameField().setVisible(true);
        view.getFacultyField().setVisible(true);
        view.getPeselField().setVisible(true);
    }





    private void openNewGroup() {
    }
}